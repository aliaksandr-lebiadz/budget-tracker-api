package org.alebiadz.budget.tracker.service.authentication

import org.alebiadz.budget.tracker.dto.user.JwtUserDto

interface JWTService {

    fun generateAccessToken(user: JwtUserDto): String

    fun generateRefreshToken(user: JwtUserDto): String

    fun decodeAccessToken(token: String): JwtUserDto

    fun decodeRefreshToken(token: String): JwtUserDto
}