package org.alebiadz.budget.tracker.service.authentication

import org.alebiadz.budget.tracker.dto.user.UserCredentialsDto
import org.alebiadz.budget.tracker.dto.user.UserTokensDto
import org.springframework.security.core.Authentication

interface AuthenticationService {

    fun getAuthenticationToken(credentials: UserCredentialsDto): Authentication

    fun getAuthenticationToken(accessToken: String): Authentication

    fun generateTokens(authentication: Authentication): UserTokensDto

    fun refreshAccessToken(refreshToken: String): String
}