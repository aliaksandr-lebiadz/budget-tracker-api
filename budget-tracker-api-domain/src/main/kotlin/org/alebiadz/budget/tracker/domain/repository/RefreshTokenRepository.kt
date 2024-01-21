package org.alebiadz.budget.tracker.domain.repository

import org.alebiadz.budget.tracker.domain.entity.RefreshTokenEntity

interface RefreshTokenRepository : GeneralJpaRepository<RefreshTokenEntity> {

    fun findByUserId(userId: Long): RefreshTokenEntity?
}
