package com.api.study.riot_api.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController {
    private val LOGGER: Logger = LoggerFactory.getLogger(AccountController::class.java)
    @PostMapping("/login")
    fun login(
        @RequestParam id: String,
        @RequestParam password: String
    ): String{
        LOGGER.info("id: $id\n password: $password")
        return "id: $id\n password: $password"
    }

    @PostMapping("/signup")
    fun signup(){

    }
}