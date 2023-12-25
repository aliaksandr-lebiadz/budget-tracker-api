package org.alebiadz.budget.tracker.domain.repository

import org.alebiadz.budget.tracker.domain.entity.BankEntity

interface BankRepository : JpaRepository<BankEntity> {

    fun existsByName(name: String): Boolean
}