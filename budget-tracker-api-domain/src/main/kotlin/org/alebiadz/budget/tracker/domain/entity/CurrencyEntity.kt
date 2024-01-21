package org.alebiadz.budget.tracker.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "currencies")
data class CurrencyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long?,

    val name: String,

    val code: String,
) : JpaEntity {

    constructor(name: String, code: String) : this(null, name, code)
}
