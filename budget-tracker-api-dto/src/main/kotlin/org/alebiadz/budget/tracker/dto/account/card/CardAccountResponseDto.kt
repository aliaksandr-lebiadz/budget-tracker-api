package org.alebiadz.budget.tracker.dto.account.card

import org.alebiadz.budget.tracker.dto.bank.BankDto
import org.alebiadz.budget.tracker.dto.card.type.CardTypeDto
import org.alebiadz.budget.tracker.dto.currency.CurrencyDto
import java.math.BigDecimal

data class CardAccountResponseDto(
    val id: Long,
    val bank: BankDto?,
    val currency: CurrencyDto,
    val cardType: CardTypeDto?,
    val cardNumber: String?,
    val balance: BigDecimal,
    val name: String?,
)
