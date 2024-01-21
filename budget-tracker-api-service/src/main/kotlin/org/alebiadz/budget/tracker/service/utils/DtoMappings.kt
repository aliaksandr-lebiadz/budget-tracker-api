package org.alebiadz.budget.tracker.service.utils

import org.alebiadz.budget.tracker.domain.entity.AccountEntity
import org.alebiadz.budget.tracker.domain.entity.AccountType
import org.alebiadz.budget.tracker.domain.entity.BankEntity
import org.alebiadz.budget.tracker.domain.entity.CardTypeEntity
import org.alebiadz.budget.tracker.domain.entity.CurrencyEntity
import org.alebiadz.budget.tracker.dto.account.card.CardAccountRequestDto
import org.alebiadz.budget.tracker.dto.account.cash.CashAccountRequestDto
import org.alebiadz.budget.tracker.dto.bank.BankDto
import org.alebiadz.budget.tracker.dto.card.type.CardTypeDto
import org.alebiadz.budget.tracker.dto.currency.CurrencyDto

fun CurrencyDto.toEntity(): CurrencyEntity = CurrencyEntity(
    name = name,
    code = code
)

fun CardTypeDto.toEntity(): CardTypeEntity = CardTypeEntity(
    name = name,
    icon = icon
)

fun BankDto.toEntity(): BankEntity = BankEntity(
    name = name,
    icon = icon
)

fun CardAccountRequestDto.toEntity(
    userId: Long,
    bankEntity: BankEntity,
    currencyEntity: CurrencyEntity,
    cardTypeEntity: CardTypeEntity
): AccountEntity = AccountEntity(
    userId = userId,
    bank = bankEntity,
    currency = currencyEntity,
    cardType = cardTypeEntity,
    type = AccountType.CARD,
    cardNumber = cardNumber,
    balance = balance,
    name = name,
)

fun CashAccountRequestDto.toEntity(
    userId: Long,
    currencyEntity: CurrencyEntity,
): AccountEntity = AccountEntity(
    userId = userId,
    currency = currencyEntity,
    type = AccountType.CASH,
    balance = balance,
)
