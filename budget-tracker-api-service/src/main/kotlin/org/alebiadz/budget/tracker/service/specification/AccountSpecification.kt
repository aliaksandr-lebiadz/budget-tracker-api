package org.alebiadz.budget.tracker.service.specification

import org.alebiadz.budget.tracker.domain.entity.AccountEntity
import org.alebiadz.budget.tracker.domain.entity.AccountType
import org.alebiadz.budget.tracker.domain.entity.BankEntity
import org.alebiadz.budget.tracker.domain.entity.CardTypeEntity
import org.alebiadz.budget.tracker.domain.entity.CurrencyEntity
import org.alebiadz.budget.tracker.dto.account.AccountFilter
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class AccountSpecification(
    private val filter: AccountFilter,
    private val type: AccountType? = null,
    private val userId: Long? = null
) : AbstractSpecification<AccountEntity>() {

    companion object {
        const val USER_ID = "userId"
        const val ID = "id"
        const val TYPE = "type"
        const val BANK = "bank"
        const val CURRENCY = "currency"
        const val CARD_TYPE = "cardType"
    }

    override fun toPredicate(
        root: Root<AccountEntity>,
        query: CriteriaQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {

        applyDefault(root, query, criteriaBuilder)

        val predicates = mutableListOf<Predicate>()
        equalTo(root.get(USER_ID), userId, criteriaBuilder)?.also { predicates.add(it) }
        equalTo(root.get(TYPE), type, criteriaBuilder)?.also { predicates.add(it) }
        equalTo(root.get<BankEntity>(BANK).get(ID), filter.bankId, criteriaBuilder)?.also { predicates.add(it) }
        equalTo(root.get<CurrencyEntity>(CURRENCY).get(ID), filter.currencyId, criteriaBuilder)?.also { predicates.add(it) }
        equalTo(root.get<CardTypeEntity>(CARD_TYPE).get(ID), filter.cardTypeId, criteriaBuilder)?.also { predicates.add(it) }
        return criteriaBuilder.and(*predicates.toTypedArray())
    }
}