package org.alebiadz.budget.tracker.service.user

import org.alebiadz.budget.tracker.dto.user.UserCredentialsDto

interface UserService {

    fun signUp(credentials: UserCredentialsDto)
}