package org.alebiadz.budget.tracker.service.authentication.impl

import org.alebiadz.budget.tracker.commons.exception.BudgetTrackerException
import org.alebiadz.budget.tracker.commons.exception.NotFoundException
import org.alebiadz.budget.tracker.domain.entity.RefreshTokenEntity
import org.alebiadz.budget.tracker.domain.repository.RefreshTokenRepository
import org.alebiadz.budget.tracker.dto.user.UserCredentialsDto
import org.alebiadz.budget.tracker.dto.user.UserTokensDto
import org.alebiadz.budget.tracker.service.authentication.AuthenticationService
import org.alebiadz.budget.tracker.service.authentication.JWTService
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

        val username = jwtService.decodeAccessToken(accessToken)
        return UsernamePasswordAuthenticationToken(username, null, emptyList())
    }

    override fun generateTokens(authentication: Authentication): UserTokensDto {

        val username = (authentication.principal as User).username
        val accessToken = jwtService.generateAccessToken(username)
        val refreshToken = jwtService.generateRefreshToken(username)
        val refreshTokenEntity = refreshTokenRepository.findByUsername(username)
            ?.copy(token = refreshToken)
            ?: RefreshTokenEntity(username, refreshToken)
        refreshTokenRepository.save(refreshTokenEntity)

        return UserTokensDto(accessToken, refreshToken)
    }

    override fun refreshAccessToken(refreshToken: String): String {

        val username = jwtService.decodeRefreshToken(refreshToken)
        val refreshTokenEntity = refreshTokenRepository.findByUsername(username) ?: throw NotFoundException(username, RefreshTokenEntity::class)
        if (refreshTokenEntity.token != refreshToken) {
            throw BudgetTrackerException("Refresh tokens mismatch")
        }

        return jwtService.generateAccessToken(username)
    }
}