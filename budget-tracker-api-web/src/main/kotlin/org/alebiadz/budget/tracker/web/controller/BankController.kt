package org.alebiadz.budget.tracker.web.controller

import org.alebiadz.budget.tracker.dto.bank.BankDto
import org.alebiadz.budget.tracker.service.bank.BankService
import org.alebiadz.budget.tracker.web.meta.Navigation
import org.alebiadz.budget.tracker.web.security.RequiresAdminRole
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
@RequestMapping(Navigation.BANK)
@RequiresAdminRole
class BankController(private val service: BankService) {

    @GetMapping
    fun getBanks(): ResponseEntity<*> {

        val banks = service.getBanks()
        return ResponseEntity.ok(banks)
    }

    @PostMapping
    fun addBank(@Valid @RequestBody bank: BankDto): ResponseEntity<*> {

        val id = service.addBank(bank)
        return ResponseEntity.ok(id)
    }

    @PutMapping
    fun changeBank(@Valid @RequestBody bank: BankDto): ResponseEntity<*> {

        service.changeBank(bank)
        return ResponseEntity.ok().build<Unit>()
    }

    @DeleteMapping(Navigation.BY_ID)
    fun deleteBankById(@PathVariable(Navigation.ID) id: Long): ResponseEntity<*> {

        service.deleteBankById(id)
        return ResponseEntity.ok().build<Unit>()
    }
}