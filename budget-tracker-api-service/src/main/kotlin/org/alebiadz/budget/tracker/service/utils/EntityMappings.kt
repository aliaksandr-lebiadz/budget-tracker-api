package org.alebiadz.budget.tracker.service.utils

import org.alebiadz.budget.tracker.domain.entity.CurrencyEntity
import org.alebiadz.budget.tracker.dto.currency.CurrencyDto

fun CurrencyEntity.toDto(): CurrencyDto = CurrencyDto(id, name, code)