package org.alebiadz.budget.tracker.web.utils

import org.springframework.http.HttpHeaders
import javax.servlet.http.HttpServletRequest

private const val BEARER_PREFIX = "Bearer "

fun HttpServletRequest.getBearerToken(): String? {

    val authorizationHeader = getHeader(HttpHeaders.AUTHORIZATION)
    return authorizationHeader
        ?.takeIf { it.startsWith(BEARER_PREFIX) }
        ?.substring(BEARER_PREFIX.length)
}
