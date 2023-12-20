package org.alebiadz.budget.tracker.service.utils

import org.alebiadz.budget.tracker.domain.entity.CardTypeEntity
import org.alebiadz.budget.tracker.domain.entity.CurrencyEntity
import org.alebiadz.budget.tracker.dto.card.type.CardTypeDto
import org.alebiadz.budget.tracker.dto.currency.CurrencyDto

fun CurrencyDto.toEntity(): CurrencyEntity = CurrencyEntity(name, code)

fun CardTypeDto.toEntity(): CardTypeEntity = CardTypeEntity(name, icon)
