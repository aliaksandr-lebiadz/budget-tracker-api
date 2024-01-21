package org.alebiadz.budget.tracker.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "refresh_tokens")
data class RefreshTokenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long?,

    val userId: Long,

    val token: String,
) : JpaEntity {

    constructor(userId: Long, token: String) : this(null, userId, token)
}
