package org.alebiadz.budget.tracker.domain.repository

import org.alebiadz.budget.tracker.domain.entity.CardTypeEntity

interface CardTypeRepository : JpaRepository<CardTypeEntity> {

    fun existsByName(name: String): Boolean
}