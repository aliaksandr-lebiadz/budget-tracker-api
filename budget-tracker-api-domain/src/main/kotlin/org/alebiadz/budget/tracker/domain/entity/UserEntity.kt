package org.alebiadz.budget.tracker.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long?,

    val username: String,

    val password: String,

    val admin: Boolean,
) : JpaEntity {

    constructor(username: String, password: String) : this(null, username, password, false)
}
