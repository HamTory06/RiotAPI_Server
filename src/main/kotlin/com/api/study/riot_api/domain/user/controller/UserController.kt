package com.api.study.riot_api.domain.user.controller

import com.api.study.riot_api.domain.user.service.DeleteUserService
import io.swagger.v3.oas.annotations.headers.Header
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    private val deleteUserService: DeleteUserService
) {
    @DeleteMapping("/delete-user/{id}")
    fun deleteUser(@PathVariable id: String, @RequestHeader("Authorization") token: String) {
        deleteUserService.deleteUser(id, token)
    }
}