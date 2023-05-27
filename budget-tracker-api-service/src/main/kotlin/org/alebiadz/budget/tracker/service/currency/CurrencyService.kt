package org.alebiadz.budget.tracker.service.currency

import org.alebiadz.budget.tracker.dto.currency.CurrencyDto

interface CurrencyService {

    fun getCurrencies(): List<CurrencyDto>

    fun addCurrency(currency: CurrencyDto): Long

    fun changeCurrency(currency: CurrencyDto)

    fun deleteCurrencyById(id: Long)
}