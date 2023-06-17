package com.api.study.riot_api.controller

import com.api.study.riot_api.domain.dto.JwtDto
import com.api.study.riot_api.domain.dto.LoginRequestDTO
import com.api.study.riot_api.domain.dto.SignupRequestDTO
import com.api.study.riot_api.service.LoginService
import com.api.study.riot_api.service.SignupService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/account")
class AccountController {
    @Autowired
    private lateinit var signupService: SignupService
    @Autowired
    private lateinit var loginService: LoginService
    @PostMapping("/login")
    fun login(
        @RequestBody loginRequestDTO: LoginRequestDTO
    ): JwtDto {
        loginService.execute(loginRequestDTO)

        return loginService.execute(loginRequestDTO)
    }

    @PostMapping("/signup")
    fun signup(
        @RequestBody @Valid signupRequestDTO: SignupRequestDTO
    ) {
        signupService.save(signupRequestDTO)
    }
}