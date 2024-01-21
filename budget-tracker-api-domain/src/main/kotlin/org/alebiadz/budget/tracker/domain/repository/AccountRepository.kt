package org.alebiadz.budget.tracker.domain.repository

import org.alebiadz.budget.tracker.domain.entity.AccountEntity
import org.alebiadz.budget.tracker.domain.entity.AccountType
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface AccountRepository : GeneralJpaRepository<AccountEntity>, JpaSpecificationExecutor<AccountEntity> {

    fun existsByUserIdAndName(userId: Long, name: String): Boolean

    fun existsByUserIdAndCardNumber(userId: Long, cardNumber: String): Boolean

    fun existsByUserIdAndCurrencyIdAndType(userId: Long, currencyId: Long, type: AccountType): Boolean

    fun findByIdAndUserIdAndType(id: Long, userId: Long, type: AccountType): AccountEntity?

    fun deleteByIdAndUserId(id: Long, userId: Long)
}