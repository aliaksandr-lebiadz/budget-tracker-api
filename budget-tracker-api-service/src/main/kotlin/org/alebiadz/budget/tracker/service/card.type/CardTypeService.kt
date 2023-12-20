package org.alebiadz.budget.tracker.service.card.type

import org.alebiadz.budget.tracker.dto.card.type.CardTypeDto

interface CardTypeService {

    fun getCardTypes(): List<CardTypeDto>

    fun addCardType(cardType: CardTypeDto): Long

    fun changeCardType(cardType: CardTypeDto)

    fun deleteCardTypeById(id: Long)
}