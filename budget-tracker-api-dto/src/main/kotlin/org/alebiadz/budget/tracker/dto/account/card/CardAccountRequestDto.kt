package org.alebiadz.budget.tracker.dto.account.card

import java.math.BigDecimal
import javax.validation.constraints.Pattern
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

data class CardAccountRequestDto(
    val id: Long?,

    val bankId: Long,

    val currencyId: Long,

    val cardTypeId: Long,

    @field:Pattern(regexp = "\\d{16}", message = "must be 16 digits number")
    val cardNumber: String,

    @field:PositiveOrZero
    val balance: BigDecimal,

    @field:Size(min = 3, max = 16)
    val name: String,
)
