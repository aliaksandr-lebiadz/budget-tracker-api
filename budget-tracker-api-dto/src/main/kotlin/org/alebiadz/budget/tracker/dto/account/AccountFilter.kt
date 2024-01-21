package org.alebiadz.budget.tracker.dto.account

data class AccountFilter(
    val bankId: Long?,
    val currencyId: Long?,
    val cardTypeId: Long?,
) {

    data class Builder(
        var bankId: Long? = null,
        var currencyId: Long? = null,
        var cardTypeId: Long? = null,
    ) {

        fun bankId(bankId: Long) = apply { this.bankId = bankId }

        fun currencyId(currencyId: Long) = apply { this.currencyId = currencyId }

        fun cardTypeId(cardTypeId: Long) = apply { this.cardTypeId = cardTypeId }

        fun build() = AccountFilter(bankId, currencyId, cardTypeId)
    }
}
