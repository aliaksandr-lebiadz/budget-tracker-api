package org.alebiadz.budget.tracker.dto.currency

import javax.validation.constraints.Size

data class CurrencyDto(
    val id: Long?,

    @field:Size(min = 3, max = 64)
    val name: String,

    @field:Size(min = 3, max = 3)
    val code: String
)
