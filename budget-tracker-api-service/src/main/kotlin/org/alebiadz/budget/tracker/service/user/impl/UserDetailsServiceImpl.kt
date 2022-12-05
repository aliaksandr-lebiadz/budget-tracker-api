package org.alebiadz.budget.tracker.service.user.impl

import org.alebiadz.budget.tracker.domain.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val repository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {

        if (username == null) {
            throw UsernameNotFoundException("Empty username provided")
        }

        val user = repository.findByUsername(username)?: throw UsernameNotFoundException("User with username $username not found")
        return User(user.username, user.password, emptySet())
    }
}