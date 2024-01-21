package org.alebiadz.budget.tracker.service.authentication.impl

import org.alebiadz.budget.tracker.commons.exception.BudgetTrackerException
import org.alebiadz.budget.tracker.commons.exception.NotFoundException
import org.alebiadz.budget.tracker.domain.entity.RefreshTokenEntity
import org.alebiadz.budget.tracker.domain.repository.RefreshTokenRepository
import org.alebiadz.budget.tracker.dto.user.JwtUserDto
import org.alebiadz.budget.tracker.dto.user.UserCredentialsDto
import org.alebiadz.budget.tracker.dto.user.UserTokensDto
import org.alebiadz.budget.tracker.service.authentication.AuthenticationService
import org.alebiadz.budget.tracker.service.authentication.JWTService
import org.alebiadz.budget.tracker.service.meta.UserMeta
import org.alebiadz.budget.tracker.service.user.JwtUser
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

        val user = jwtService.decodeAccessToken(accessToken)
        val authorities = UserUtils.getAuthorities(user.admin)
        return UsernamePasswordAuthenticationToken(user.id, null, authorities)
    }

    override fun generateTokens(authentication: Authentication): UserTokensDto {

        val user = authentication.principal as JwtUser
        val userId = user.id
        val admin = isAdmin(user)
        val jwtUser = JwtUserDto(userId, user.username, admin)

        val accessToken = jwtService.generateAccessToken(jwtUser)
        val refreshToken = jwtService.generateRefreshToken(jwtUser)
        val refreshTokenEntity = refreshTokenRepository.findByUserId(userId)
            ?.copy(token = refreshToken)
            ?: RefreshTokenEntity(userId, refreshToken)
        refreshTokenRepository.save(refreshTokenEntity)

        return UserTokensDto(accessToken, refreshToken)
    }

    override fun refreshAccessToken(refreshToken: String): String {

        val user = jwtService.decodeRefreshToken(refreshToken)
        val userId = user.id
        val refreshTokenEntity = refreshTokenRepository.findByUserId(userId) ?: throw NotFoundException(userId, RefreshTokenEntity::class)
        if (refreshTokenEntity.token != refreshToken) {
            throw BudgetTrackerException("Refresh tokens mismatch")
        }

        return jwtService.generateAccessToken(user)
    }

    private fun isAdmin(user: User): Boolean {

        return user.authorities.stream()
            .map { it.authority }
            .anyMatch { it.equals(UserMeta.ADMIN_ROLE) }
    }
}