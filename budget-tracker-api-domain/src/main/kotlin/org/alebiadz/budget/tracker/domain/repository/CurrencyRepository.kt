package org.alebiadz.budget.tracker.domain.repository

import org.alebiadz.budget.tracker.domain.entity.CurrencyEntity

interface CurrencyRepository : JpaRepository<CurrencyEntity> {

    fun existsByName(name: String): Boolean

    fun existsByCode(code: String): Boolean
}