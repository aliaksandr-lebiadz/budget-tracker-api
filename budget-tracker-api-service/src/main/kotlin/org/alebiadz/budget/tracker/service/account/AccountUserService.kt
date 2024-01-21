package org.alebiadz.budget.tracker.service.account

import org.alebiadz.budget.tracker.dto.account.AccountFilter
import org.alebiadz.budget.tracker.dto.account.card.CardAccountRequestDto
import org.alebiadz.budget.tracker.dto.account.card.CardAccountResponseDto
import org.alebiadz.budget.tracker.dto.account.cash.CashAccountRequestDto
import org.alebiadz.budget.tracker.dto.account.cash.CashAccountResponseDto

interface AccountUserService {

    fun getCardAccounts(filter: AccountFilter): List<CardAccountResponseDto>

    fun getCashAccounts(): List<CashAccountResponseDto>

    fun addCardAccount(account: CardAccountRequestDto): Long

    fun addCashAccount(account: CashAccountRequestDto): Long

    fun changeCardAccount(account: CardAccountRequestDto)

    fun changeCashAccount(account: CashAccountRequestDto)

    fun deleteAccountById(id: Long)
}