package org.alebiadz.budget.tracker.service.card.type.impl

import org.alebiadz.budget.tracker.commons.exception.AlreadyExistsException
import org.alebiadz.budget.tracker.commons.exception.NotFoundException
import org.alebiadz.budget.tracker.commons.exception.ObjectNullException
import org.alebiadz.budget.tracker.domain.entity.CardTypeEntity
import org.alebiadz.budget.tracker.domain.repository.CardTypeRepository
import org.alebiadz.budget.tracker.dto.card.type.CardTypeDto
import org.alebiadz.budget.tracker.service.card.type.CardTypeService
import org.alebiadz.budget.tracker.service.utils.copy
import org.alebiadz.budget.tracker.service.utils.toDto
import org.alebiadz.budget.tracker.service.utils.toEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CardTypeServiceImpl(private val repository: CardTypeRepository) : CardTypeService {

    override fun getCardTypes(): List<CardTypeDto> {

        return repository.findAll().map { it.toDto() }
    }

    override fun addCardType(cardType: CardTypeDto): Long {

        validateUniqueness(cardType)

        val entity = cardType.toEntity().let { repository.save(it) }
        return entity.id!!
    }

    override fun changeCardType(cardType: CardTypeDto) {

        val id = cardType.id ?: throw ObjectNullException("id")
        val entity = repository.findByIdOrNull(id)
            ?.also { validateUniqueness(cardType, it) }
            ?.copy(cardType)
            ?: throw NotFoundException(id, CardTypeEntity::class)
        repository.save(entity)
    }

    override fun deleteCardTypeById(id: Long) {

        repository.deleteById(id)
    }

    private fun validateUniqueness(cardType: CardTypeDto, entity: CardTypeEntity? = null) {

        val nameChanged = cardType.name != entity?.name
        val existsByName = nameChanged && repository.existsByName(cardType.name)
        if (existsByName) {
            throw AlreadyExistsException("Card type with name [${cardType.name}] already exists")
        }
    }
}