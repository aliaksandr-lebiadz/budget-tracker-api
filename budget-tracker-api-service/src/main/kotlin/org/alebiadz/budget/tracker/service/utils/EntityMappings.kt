package org.alebiadz.budget.tracker.service.utils

import org.alebiadz.budget.tracker.domain.entity.AccountEntity
import org.alebiadz.budget.tracker.domain.entity.AccountType
import org.alebiadz.budget.tracker.domain.entity.BankEntity
import org.alebiadz.budget.tracker.domain.entity.CardTypeEntity
import org.alebiadz.budget.tracker.domain.entity.CurrencyEntity
import org.alebiadz.budget.tracker.dto.account.AccountShortResponseDto
import org.alebiadz.budget.tracker.dto.account.card.CardAccountRequestDto
import org.alebiadz.budget.tracker.dto.account.card.CardAccountResponseDto
import org.alebiadz.budget.tracker.dto.account.cash.CashAccountRequestDto
import org.alebiadz.budget.tracker.dto.account.cash.CashAccountResponseDto
import org.alebiadz.budget.tracker.dto.bank.BankDto
import org.alebiadz.budget.tracker.dto.card.type.CardTypeDto
import org.alebiadz.budget.tracker.dto.currency.CurrencyDto

fun CurrencyEntity.toDto(): CurrencyDto = CurrencyDto(
    id = id,
    name = name,
    code = code,
)

fun CurrencyEntity.copy(dto: CurrencyDto): CurrencyEntity = copy(
    name = dto.name,
    code = dto.code,
)

fun CardTypeEntity.toDto(): CardTypeDto = CardTypeDto(
    id = id,
    name = name,
    icon = icon,
)

fun CardTypeEntity.copy(dto: CardTypeDto): CardTypeEntity = copy(
    name = dto.name,
    icon = dto.icon,
)

fun BankEntity.toDto(): BankDto = BankDto(
    id = id,
    name = name,
    icon = icon,
)

fun BankEntity.copy(dto: BankDto): BankEntity = copy(
    name = dto.name,
    icon = dto.icon,
)

fun AccountEntity.toCardDto(): CardAccountResponseDto = CardAccountResponseDto(
    id = id!!,
    bank = bank?.toDto(),
    currency = currency.toDto(),
    cardType = cardType?.toDto(),
    cardNumber = cardNumber,
    balance = balance,
    name = name,
)

fun AccountEntity.toCashDto(): CashAccountResponseDto = CashAccountResponseDto(
    id = id!!,
    currency = currency.toDto(),
    balance = balance,
)

fun AccountEntity.toShortDto(): AccountShortResponseDto = AccountShortResponseDto(
    id = id!!,
    type = type.text,
    currencyId = currency.id!!,
    name = name,
)

fun AccountEntity.copy(
    dto: CardAccountRequestDto,
    bankEntity: BankEntity,
    currencyEntity: CurrencyEntity,
    cardTypeEntity: CardTypeEntity
): AccountEntity = copy(
    bank = bankEntity,
    currency = currencyEntity,
    cardType = cardTypeEntity,
    type = AccountType.CARD,
    cardNumber = dto.cardNumber,
    balance = dto.balance,
    name = dto.name
)

fun AccountEntity.copy(
    dto: CashAccountRequestDto,
    currencyEntity: CurrencyEntity,
): AccountEntity = copy(
    currency = currencyEntity,
    type = AccountType.CASH,
    balance = dto.balance,
)
