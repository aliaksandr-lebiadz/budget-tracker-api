package org.alebiadz.budget.tracker.domain.repository

import org.alebiadz.budget.tracker.domain.entity.JpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface GeneralJpaRepository<T : JpaEntity> : JpaRepository<T, Long> {

    @Query("SELECT e from #{#entityName} e order by e.id")
    override fun findAll(): List<T>
}