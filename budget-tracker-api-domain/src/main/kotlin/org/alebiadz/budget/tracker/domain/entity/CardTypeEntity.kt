package org.alebiadz.budget.tracker.domain.entity

import org.hibernate.annotations.Type
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob
import javax.persistence.Table

@Entity
@Table(name = "card_types")
@Suppress("ArrayInDataClass")
data class CardTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long?,

    val name: String,

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    val icon: ByteArray,
) : JpaEntity {

    constructor(name: String, icon: ByteArray) : this(null, name, icon)
}
