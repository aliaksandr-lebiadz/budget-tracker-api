package org.alebiadz.budget.tracker.domain.entity

import org.alebiadz.budget.tracker.domain.converter.EnumConverter
import java.math.BigDecimal
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "accounts")
data class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long?,

    val userId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    val bank: BankEntity?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    val currency: CurrencyEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_type_id")
    val cardType: CardTypeEntity?,

    @Convert(converter = EnumConverter::class)
    val type: AccountType,

    val cardNumber: String?,

    val balance: BigDecimal,

    val name: String?,
) : JpaEntity {

    constructor(
        userId: Long,
        bank: BankEntity,
        currency: CurrencyEntity,
        cardType: CardTypeEntity,
        type: AccountType,
        cardNumber: String,
        balance: BigDecimal,
        name: String
    ) : this(null, userId, bank, currency, cardType, type, cardNumber, balance, name)

    constructor(
        userId: Long,
        currency: CurrencyEntity,
        type: AccountType,
        balance: BigDecimal,
    ) : this(null, userId, null, currency, null, type, null, balance, null)
}
