package org.alebiadz.budget.tracker.service.user.impl

import org.alebiadz.budget.tracker.commons.exception.BudgetTrackerException
import org.alebiadz.budget.tracker.domain.entity.UserEntity
import org.alebiadz.budget.tracker.domain.repository.UserRepository
import org.alebiadz.budget.tracker.dto.user.UserCredentialsDto
import org.alebiadz.budget.tracker.service.user.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val repository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun signUp(credentials: UserCredentialsDto) {

        repository.findByUsername(credentials.username)
            ?.also { throw BudgetTrackerException("User with username ${credentials.username} already exists") }

        val encodedPassword = passwordEncoder.encode(credentials.password)
        val user = UserEntity(credentials.username, encodedPassword)
        repository.save(user)
    }
}