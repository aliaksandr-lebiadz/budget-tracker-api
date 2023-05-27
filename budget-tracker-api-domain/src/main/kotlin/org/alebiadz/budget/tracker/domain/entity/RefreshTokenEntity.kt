package org.alebiadz.budget.tracker.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "refresh_tokens")
data class RefreshTokenEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) override var id: Long?,
    var username: String,
    val token: String
) : JpaEntity {

    constructor(username: String, token: String) : this(null, username, token)
}
