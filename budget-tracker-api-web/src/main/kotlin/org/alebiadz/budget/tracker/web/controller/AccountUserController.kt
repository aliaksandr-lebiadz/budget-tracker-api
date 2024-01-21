package org.alebiadz.budget.tracker.web.controller

import org.alebiadz.budget.tracker.dto.account.AccountFilter
import org.alebiadz.budget.tracker.dto.account.card.CardAccountRequestDto
import org.alebiadz.budget.tracker.dto.account.cash.CashAccountRequestDto
import org.alebiadz.budget.tracker.service.account.AccountUserService
import org.alebiadz.budget.tracker.web.meta.Navigation
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
@RequestMapping(Navigation.ACCOUNT_USER)
@RequiresAuthentication
class AccountUserController(private val service: AccountUserService) {

    @GetMapping(Navigation.CARD)
    fun getCardAccounts(filter: AccountFilter): ResponseEntity<*> {

        val accounts = service.getCardAccounts(filter)
        return ResponseEntity.ok(accounts)
    }

    @GetMapping(Navigation.CASH)
    fun getCashAccounts(): ResponseEntity<*> {

        val accounts = service.getCashAccounts()
        return ResponseEntity.ok(accounts)
    }

    @PostMapping(Navigation.CARD)
    fun addCardAccount(@Valid @RequestBody account: CardAccountRequestDto): ResponseEntity<*> {

        val id = service.addCardAccount(account)
        return ResponseEntity.ok(id)
    }

    @PostMapping(Navigation.CASH)
    fun addCashAccount(@Valid @RequestBody account: CashAccountRequestDto): ResponseEntity<*> {

        val id = service.addCashAccount(account)
        return ResponseEntity.ok(id)
    }

    @PutMapping(Navigation.CARD)
    fun changeCardAccount(@Valid @RequestBody account: CardAccountRequestDto): ResponseEntity<*> {

        service.changeCardAccount(account)
        return ResponseEntity.ok().build<Unit>()
    }

    @PutMapping(Navigation.CASH)
    fun changeCashAccount(@Valid @RequestBody account: CashAccountRequestDto): ResponseEntity<*> {

        service.changeCashAccount(account)
        return ResponseEntity.ok().build<Unit>()
    }

    @DeleteMapping(Navigation.BY_ID)
    fun deleteAccountById(@PathVariable(Navigation.ID) id: Long): ResponseEntity<*> {

        service.deleteAccountById(id)
        return ResponseEntity.ok().build<Unit>()
    }
}