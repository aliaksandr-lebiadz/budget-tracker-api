package org.alebiadz.budget.tracker.dto.user

data class UserTokensDto(
    val accessToken: String,
    val refreshToken: String
)