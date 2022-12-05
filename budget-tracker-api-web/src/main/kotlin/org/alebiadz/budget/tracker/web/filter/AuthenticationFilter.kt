package org.alebiadz.budget.tracker.web.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.alebiadz.budget.tracker.dto.user.UserCredentialsDto
import org.alebiadz.budget.tracker.service.authentication.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    private val service: AuthenticationService,
    private val manager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter() {

    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

        val credentials = getCredentials(request!!)
        val token = service.getAuthenticationToken(credentials)
        return manager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {

        val tokens = service.generateTokens(authResult!!)
        response?.contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(response?.outputStream, tokens)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        response?.status = HttpStatus.FORBIDDEN.value()
        response?.writer?.write(failed?.message!!)
    }

    private fun getCredentials(request: HttpServletRequest): UserCredentialsDto {

        val body: String = request.reader.lines().collect(Collectors.joining())
        return objectMapper.readValue(body, UserCredentialsDto::class.java)
    }
}