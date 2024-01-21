package org.alebiadz.budget.tracker.service.account.impl

import org.alebiadz.budget.tracker.domain.repository.AccountRepository
import org.alebiadz.budget.tracker.dto.account.AccountFilter
import org.alebiadz.budget.tracker.dto.account.AccountShortResponseDto
import org.alebiadz.budget.tracker.service.account.AccountAdminService
import org.alebiadz.budget.tracker.service.specification.AccountSpecification
import org.alebiadz.budget.tracker.service.utils.toShortDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountAdminServiceImpl(private val accountRepository: AccountRepository) : AccountAdminService {

    override fun getAccounts(filter: AccountFilter): List<AccountShortResponseDto> {

        val specification = AccountSpecification(filter)
        return accountRepository.findAll(specification).map { it.toShortDto() }
    }

    @Transactional
    override fun deleteAccounts(ids: Set<Long>) {

        accountRepository.deleteAllByIdInBatch(ids)
    }
}