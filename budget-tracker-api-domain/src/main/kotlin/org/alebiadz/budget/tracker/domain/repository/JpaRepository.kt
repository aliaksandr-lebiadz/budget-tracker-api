package org.alebiadz.budget.tracker.domain.repository

import org.alebiadz.budget.tracker.domain.entity.JpaEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface JpaRepository<T : JpaEntity> : CrudRepository<T, Long> {

    @Query("SELECT e from #{#entityName} e order by e.id")
    override fun findAll(): List<T>
}