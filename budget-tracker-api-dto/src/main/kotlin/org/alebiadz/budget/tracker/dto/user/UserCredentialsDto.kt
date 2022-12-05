package org.alebiadz.budget.tracker.dto.user

import javax.validation.constraints.Size

data class UserCredentialsDto(
    @field:Size(min = 6, max = 32)
    val username: String,

    @field:Size(min = 8, max = 32)
    val password: String
)

