package org.alebiadz.budget.tracker.service.bank

import org.alebiadz.budget.tracker.dto.bank.BankDto

interface BankService {

    fun getBanks(): List<BankDto>

    fun addBank(bank: BankDto): Long

    fun changeBank(bank: BankDto)

    fun deleteBankById(id: Long)
}