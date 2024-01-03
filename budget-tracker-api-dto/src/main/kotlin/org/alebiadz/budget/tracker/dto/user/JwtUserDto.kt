package org.alebiadz.budget.tracker.dto.user

data class JwtUserDto(
    val id: Long,
    val username: String,
    val admin: Boolean,
)
