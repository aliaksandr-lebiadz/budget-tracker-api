package org.alebiadz.budget.tracker.service.authentication

interface JWTService {

    fun generateAccessToken(username: String): String

    fun generateRefreshToken(username: String): String

    fun decodeAccessToken(token: String): String

    fun decodeRefreshToken(token: String): String
}