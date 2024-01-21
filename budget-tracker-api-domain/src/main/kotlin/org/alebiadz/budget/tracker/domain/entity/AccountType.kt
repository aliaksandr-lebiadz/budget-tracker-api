package org.alebiadz.budget.tracker.domain.entity

import org.alebiadz.budget.tracker.commons.HasText

enum class AccountType(override val text: String) : HasText {

    CARD("card"),
    CASH("cash");
}
