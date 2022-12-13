package org.alebiadz.budget.tracker.service.authentication.impl

import org.alebiadz.budget.tracker.commons.exception.BudgetTrackerException
import org.alebiadz.budget.tracker.commons.exception.NotFoundException
import org.alebiadz.budget.tracker.domain.entity.RefreshTokenEntity
import org.alebiadz.budget.tracker.domain.repository.RefreshTokenRepository
import org.alebiadz.budget.tracker.dto.user.UserCredentialsDto
import org.alebiadz.budget.tracker.dto.user.UserTokensDto
import org.alebiadz.budget.tracker.service.authentication.AuthenticationService
import org.alebiadz.budget.tracker.service.authentication.JWTService
import org.alebiadz.budget.tracker.service.meta.UserMeta
import org.alebiadz.budget.tracker.service.utils.UserUtils
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtService: JWTService
) : AuthenticationService {

    override fun getAuthenticationToken(credentials: UserCredentialsDto): Authentication {

        return UsernamePasswordAuthenticationToken(credentials.username, credentials.password)
    }

    override fun getAuthenticationToken(accessToken: String): Authentication {

        val (username, admin) = jwtService.decodeAccessToken(accessToken)
        val authorities = UserUtils.getAuthorities(admin)
        return UsernamePasswordAuthenticationToken(username, null, authorities)
    }

    override fun generateTokens(authentication: Authentication): UserTokensDto {

        val user = authentication.principal as User
        val username = user.username
        val admin = isAdmin(user)

        val accessToken = jwtService.generateAccessToken(username, admin)
        val refreshToken = jwtService.generateRefreshToken(username, admin)
        val refreshTokenEntity = refreshTokenRepository.findByUsername(username)
            ?.copy(token = refreshToken)
            ?: RefreshTokenEntity(username, refreshToken)
        refreshTokenRepository.save(refreshTokenEntity)

        return UserTokensDto(accessToken, refreshToken)
    }

    override fun refreshAccessToken(refreshToken: String): String {

        val (username, admin) = jwtService.decodeRefreshToken(refreshToken)
        val refreshTokenEntity = refreshTokenRepository.findByUsername(username) ?: throw NotFoundException(username, RefreshTokenEntity::class)
        if (refreshTokenEntity.token != refreshToken) {
            throw BudgetTrackerException("Refresh tokens mismatch")
        }

        return jwtService.generateAccessToken(username, admin)
    }

    private fun isAdmin(user: User): Boolean {

        return user.authorities.stream()
            .map { it.authority }
            .anyMatch { it.equals(UserMeta.ROLE_ADMIN) }
    }
}