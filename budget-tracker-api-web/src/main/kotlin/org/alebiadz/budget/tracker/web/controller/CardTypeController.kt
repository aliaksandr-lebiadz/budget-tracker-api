package org.alebiadz.budget.tracker.web.controller

import org.alebiadz.budget.tracker.dto.card.type.CardTypeDto
import org.alebiadz.budget.tracker.service.card.type.CardTypeService
import org.alebiadz.budget.tracker.web.meta.Navigation
import org.alebiadz.budget.tracker.web.security.RequiresAdminRole
import org.alebiadz.budget.tracker.web.security.RequiresAuthentication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(Navigation.CARD_TYPE)
@RequiresAuthentication
class CardTypeController(private val service: CardTypeService) {

    @GetMapping
    fun getCardTypes(): ResponseEntity<*> {

        val cardTypes = service.getCardTypes()
        return ResponseEntity.ok(cardTypes)
    }

    @PostMapping
    @RequiresAdminRole
    fun addCardType(@Valid @RequestBody cardType: CardTypeDto): ResponseEntity<*> {

        val id = service.addCardType(cardType)
        return ResponseEntity.ok(id)
    }

    @PutMapping
    @RequiresAdminRole
    fun changeCardType(@Valid @RequestBody cardType: CardTypeDto): ResponseEntity<*> {

        service.changeCardType(cardType)
        return ResponseEntity.ok().build<Unit>()
    }

    @DeleteMapping(Navigation.BY_ID)
    @RequiresAdminRole
    fun deleteCardTypeById(@PathVariable(Navigation.ID) id: Long): ResponseEntity<*> {

        service.deleteCardTypeById(id)
        return ResponseEntity.ok().build<Unit>()
    }
}