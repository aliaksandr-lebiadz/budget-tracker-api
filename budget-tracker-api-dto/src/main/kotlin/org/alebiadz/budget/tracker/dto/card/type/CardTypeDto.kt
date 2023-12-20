package org.alebiadz.budget.tracker.dto.card.type

import javax.validation.constraints.Size

@Suppress("ArrayInDataClass")
data class CardTypeDto(
    val id: Long?,

    @field:Size(min = 4, max = 32)
    val name: String,

    val icon: ByteArray
)
