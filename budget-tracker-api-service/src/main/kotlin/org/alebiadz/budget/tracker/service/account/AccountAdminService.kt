package org.alebiadz.budget.tracker.service.account

import org.alebiadz.budget.tracker.dto.account.AccountFilter
import org.alebiadz.budget.tracker.dto.account.AccountShortResponseDto

interface AccountAdminService {

    fun getAccounts(filter: AccountFilter): List<AccountShortResponseDto>

    fun deleteAccounts(ids: Set<Long>)
}