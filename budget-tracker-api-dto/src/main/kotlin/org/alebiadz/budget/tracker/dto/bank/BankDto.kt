package org.alebiadz.budget.tracker.dto.bank

import javax.validation.constraints.Size

@Suppress("ArrayInDataClass")
data class BankDto(
    val id: Long?,

    @field:Size(min = 3, max = 128)
    val name: String,

    val icon: ByteArray
)
