package org.alebiadz.budget.tracker.web.meta

object Navigation {

    // controllers:
    const val USER = "/user"
    const val CURRENCY = "/currency"
    const val CARD_TYPE = "/card-type"
    const val BANK = "/bank"

    // variables:
    const val ID = "id"
    const val ID_PATH_PARAM = "{$ID}"

    // endpoints:
    const val SIGN_UP = "/sign-up"
    const val LOGIN = "/login"
    const val REFRESH_TOKEN = "/token/refresh"
    const val BY_ID = "/$ID_PATH_PARAM"

    // patterns:
    const val USER_PATH_PATTERN = "$USER/**"
    const val CURRENCY_PATTERN = "$CURRENCY/**"
    const val CARD_TYPE_PATTERN = "$CARD_TYPE/**"
    const val BANK_PATTERN = "$BANK/**"
}