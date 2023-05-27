package org.alebiadz.budget.tracker.domain.repository

import org.alebiadz.budget.tracker.domain.entity.UserEntity

interface UserRepository : JpaRepository<UserEntity> {

    fun findByUsername(username: String): UserEntity?
}
