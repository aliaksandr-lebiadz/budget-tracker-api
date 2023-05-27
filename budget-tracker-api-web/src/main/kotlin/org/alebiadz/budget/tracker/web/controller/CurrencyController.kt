package org.alebiadz.budget.tracker.web.controller

import org.alebiadz.budget.tracker.dto.currency.CurrencyDto
import org.alebiadz.budget.tracker.service.currency.CurrencyService
import org.alebiadz.budget.tracker.web.meta.Navigation
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
@RequestMapping(Navigation.CURRENCY)
class CurrencyController(
    private val service: CurrencyService
) {

    @GetMapping
    fun getCurrencies(): ResponseEntity<*> {

        val currencies = service.getCurrencies()
        return ResponseEntity.ok(currencies)
    }

    @PostMapping
    fun addCurrency(@Valid @RequestBody currency: CurrencyDto): ResponseEntity<*> {

        val id = service.addCurrency(currency)
        return ResponseEntity.ok(id)
    }

    @PutMapping
    fun changeCurrency(@Valid @RequestBody currency: CurrencyDto): ResponseEntity<*> {

        service.changeCurrency(currency)
        return ResponseEntity.ok().build<Unit>()
    }

    @DeleteMapping(Navigation.BY_ID)
    fun deleteCurrencyById(@PathVariable(Navigation.ID) id: Long): ResponseEntity<*> {

        service.deleteCurrencyById(id)
        return ResponseEntity.ok().build<Unit>()
    }
}