package org.alebiadz.budget.tracker.web.controller

import org.alebiadz.budget.tracker.dto.account.AccountFilter
import org.alebiadz.budget.tracker.service.account.AccountAdminService
import org.alebiadz.budget.tracker.web.meta.Navigation
import org.alebiadz.budget.tracker.web.security.RequiresAdminRole
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Navigation.ACCOUNT_ADMIN)
@RequiresAdminRole
class AccountAdminController(private val service: AccountAdminService) {

    @GetMapping
    fun getAccounts(filter: AccountFilter): ResponseEntity<*> {

        val accounts = service.getAccounts(filter)
        return ResponseEntity.ok(accounts)
    }
}