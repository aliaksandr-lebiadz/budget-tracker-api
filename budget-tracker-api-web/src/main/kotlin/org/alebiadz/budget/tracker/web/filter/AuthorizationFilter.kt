package org.alebiadz.budget.tracker.web.filter

import org.alebiadz.budget.tracker.web.meta.Navigation
import org.alebiadz.budget.tracker.service.authentication.AuthenticationService
import org.alebiadz.budget.tracker.web.utils.getBearerToken
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter(private val service: AuthenticationService) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val path = request.requestURI.substringAfter(request.contextPath)
        if (!path.startsWith(Navigation.USER)) {
            try {
                request.getBearerToken()
                    ?.let { service.getAuthenticationToken(it) }
                    ?.also { SecurityContextHolder.getContext().authentication = it }
                    ?.also { filterChain.doFilter(request, response) }
                    ?: fillForbiddenResponse(response, "Forbidden")
            } catch (e: Exception) {
                fillForbiddenResponse(response, e.message!!)
            }
        } else {
            filterChain.doFilter(request, response)
        }
    }

    private fun fillForbiddenResponse(response: HttpServletResponse, message: String) {

        response.status = HttpStatus.FORBIDDEN.value()
        response.writer.write(message)
    }
}