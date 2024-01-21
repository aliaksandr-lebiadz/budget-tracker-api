package org.alebiadz.budget.tracker.dto.account

data class AccountShortResponseDto(
    val id: Long,
    val type: String,
    val currencyId: Long,
    val name: String?,
)
