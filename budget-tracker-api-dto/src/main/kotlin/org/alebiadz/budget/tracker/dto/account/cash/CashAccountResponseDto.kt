package org.alebiadz.budget.tracker.dto.account.cash

import org.alebiadz.budget.tracker.dto.currency.CurrencyDto
import java.math.BigDecimal

data class CashAccountResponseDto(
    val id: Long,
    val currency: CurrencyDto,
    val balance: BigDecimal,
)
