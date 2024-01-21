package org.alebiadz.budget.tracker.dto.account.cash

import java.math.BigDecimal
import javax.validation.constraints.PositiveOrZero

data class CashAccountRequestDto(
    val id: Long?,

    val currencyId: Long,

    @field:PositiveOrZero
    val balance: BigDecimal,
)
