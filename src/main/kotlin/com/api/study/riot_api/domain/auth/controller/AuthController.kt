package com.api.study.riot_api.domain.auth.controller

import com.api.study.riot_api.domain.auth.controller.dto.response.LoginDto
import com.api.study.riot_api.domain.auth.controller.dto.response.SignupDto
import com.api.study.riot_api.domain.auth.controller.dto.response.TokenResponseDto
import com.api.study.riot_api.domain.auth.service.LoginService
import com.api.study.riot_api.domain.auth.service.SignupService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val loginService: LoginService,
    private val signupService: SignupService,
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDto): TokenResponseDto {
        return loginService.login(loginDto)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid signupDto: SignupDto){
        signupService.signup(signupDto)
    }

}