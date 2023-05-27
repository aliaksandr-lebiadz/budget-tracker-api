package org.alebiadz.budget.tracker.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) override var id: Long?,
    var username: String,
    var password: String,
    var admin: Boolean,
) : JpaEntity {

    constructor(username: String, password: String) : this(null, username, password, false)
}
