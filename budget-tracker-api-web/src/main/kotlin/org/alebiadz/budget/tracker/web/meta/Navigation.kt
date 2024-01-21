package org.alebiadz.budget.tracker.web.meta

object Navigation {

    // controllers:
    private const val ACCOUNT = "/account"

    const val USER = "/user"
    const val CURRENCY = "/currency"
    const val CARD_TYPE = "/card-type"
    const val BANK = "/bank"
    const val ACCOUNT_USER = ACCOUNT + USER
    const val ACCOUNT_ADMIN = "$ACCOUNT/admin"

    // variables:
    const val ID = "id"
    private const val ID_PATH_PARAM = "{$ID}"

    // endpoints:
    const val SIGN_UP = "/sign-up"
    const val LOGIN = "/login"
    const val REFRESH_TOKEN = "/token/refresh"
    const val BY_ID = "/$ID_PATH_PARAM"
    const val CARD = "/card"
    const val CASH = "/cash"
}