package com.api.study.riot_api.domain.auth.controller

import com.api.study.riot_api.domain.auth.dto.response.LoginDto
import com.api.study.riot_api.domain.auth.dto.response.SignupDto
import com.api.study.riot_api.domain.auth.service.DeleteUserService
import com.api.study.riot_api.domain.auth.service.LoginService
import com.api.study.riot_api.domain.auth.service.SignupService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val loginService: LoginService,
    private val signupService: SignupService,
    private val deleteUserService: DeleteUserService
) {
    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto){
        loginService
    }

    @PostMapping("/signup")
    fun signup(@RequestBody signupDto: SignupDto){
        signupService
    }

    @DeleteMapping("/delete-user/{id}")
    fun deleteUser(@PathVariable id: String){
        deleteUserService.deleteUser(id)
    }
}