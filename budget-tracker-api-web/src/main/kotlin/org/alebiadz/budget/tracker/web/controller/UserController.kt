package org.alebiadz.budget.tracker.web.controller

import org.alebiadz.budget.tracker.dto.common.StringWrapper
import org.alebiadz.budget.tracker.dto.user.UserCredentialsDto
import org.alebiadz.budget.tracker.service.authentication.AuthenticationService
import org.alebiadz.budget.tracker.service.user.UserService
import org.alebiadz.budget.tracker.web.meta.Navigation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(Navigation.USER)
class UserController(
    private val userService: UserService,
    private val authenticationService: AuthenticationService
) {

    @PostMapping(Navigation.SIGN_UP)
    fun signUp(@Valid @RequestBody credentials: UserCredentialsDto): ResponseEntity<*> {

        userService.signUp(credentials)
        return ResponseEntity.ok().build<Unit>()
    }

    @PostMapping(Navigation.REFRESH_TOKEN)
    fun refreshToken(@RequestBody refreshTokenWrapper: StringWrapper): ResponseEntity<*> {

        val accessToken = authenticationService.refreshAccessToken(refreshTokenWrapper.value)
        return ResponseEntity.ok(accessToken)
    }
}