package org.alebiadz.budget.tracker.domain.repository

import org.alebiadz.budget.tracker.domain.entity.BankEntity

interface BankRepository : GeneralJpaRepository<BankEntity> {

    fun existsByName(name: String): Boolean
}