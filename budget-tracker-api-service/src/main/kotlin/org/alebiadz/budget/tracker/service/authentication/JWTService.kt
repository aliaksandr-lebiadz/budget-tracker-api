package org.alebiadz.budget.tracker.service.authentication

import org.alebiadz.budget.tracker.dto.user.DecodedUserInfoDto

interface JWTService {

    fun generateAccessToken(username: String, admin: Boolean): String

    fun generateRefreshToken(username: String, admin: Boolean): String

    fun decodeAccessToken(token: String): DecodedUserInfoDto

    fun decodeRefreshToken(token: String): DecodedUserInfoDto
}