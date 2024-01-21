package org.alebiadz.budget.tracker.web.configuration

import org.alebiadz.budget.tracker.service.authentication.AuthenticationService
import org.alebiadz.budget.tracker.web.filter.AuthenticationFilter
import org.alebiadz.budget.tracker.web.filter.AuthorizationFilter
import org.alebiadz.budget.tracker.web.meta.Navigation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
class WebSecurityConfiguration(private val authenticationService: AuthenticationService) {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(
        http: HttpSecurity,
        userDetailsService: UserDetailsService,
        passwordEncoder: PasswordEncoder
    ): AuthenticationManager {

        val builder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        return builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder).and().build()
    }

    @Bean
    fun filterChain(http: HttpSecurity, authenticationManager: AuthenticationManager): SecurityFilterChain {

        http.csrf().disable()
        http.cors().configurationSource { corsConfiguration() }
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.authorizeRequests().anyRequest().permitAll()

        http.addFilter(createAuthenticationFilter(authenticationManager))
        http.addFilterAfter(createAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    private fun createAuthenticationFilter(authenticationManager: AuthenticationManager): AuthenticationFilter {

        val filter = AuthenticationFilter(authenticationService, authenticationManager)
        filter.setFilterProcessesUrl(Navigation.USER + Navigation.LOGIN)
        return filter
    }

    private fun createAuthorizationFilter(): AuthorizationFilter {

        return AuthorizationFilter(authenticationService)
    }

    private fun corsConfiguration(): CorsConfiguration {

        val configuration = CorsConfiguration()
        HttpMethod.values().forEach { configuration.addAllowedMethod(it) }
        return configuration.applyPermitDefaultValues()
    }
}