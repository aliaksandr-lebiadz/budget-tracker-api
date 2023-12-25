package org.alebiadz.budget.tracker.service.utils

import org.alebiadz.budget.tracker.domain.entity.BankEntity
import org.alebiadz.budget.tracker.domain.entity.CardTypeEntity
import org.alebiadz.budget.tracker.domain.entity.CurrencyEntity
import org.alebiadz.budget.tracker.dto.bank.BankDto
import org.alebiadz.budget.tracker.dto.card.type.CardTypeDto
import org.alebiadz.budget.tracker.dto.currency.CurrencyDto

fun CurrencyEntity.toDto(): CurrencyDto = CurrencyDto(id, name, code)

fun CurrencyEntity.copy(dto: CurrencyDto): CurrencyEntity = copy(
    name = dto.name,
    code = dto.code,
)

fun CardTypeEntity.toDto(): CardTypeDto = CardTypeDto(id, name, icon)

fun CardTypeEntity.copy(dto: CardTypeDto): CardTypeEntity = copy(
    name = dto.name,
    icon = dto.icon,
)

fun BankEntity.toDto(): BankDto = BankDto(id, name, icon)

fun BankEntity.copy(dto: BankDto): BankEntity = copy(
    name = dto.name,
    icon = dto.icon,
)
