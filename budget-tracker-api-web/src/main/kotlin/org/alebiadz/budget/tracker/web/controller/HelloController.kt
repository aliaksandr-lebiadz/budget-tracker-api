package org.alebiadz.budget.tracker.web.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {

    @GetMapping
    fun hello(): ResponseEntity<*> {

        return ResponseEntity.ok("hello")
    }

    @GetMapping("/admin")
    fun helloAdmin(): ResponseEntity<*> {

        return ResponseEntity.ok("hello, admin")
    }
}