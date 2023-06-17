package com.api.study.riot_api.controller

import com.api.study.riot_api.domain.dto.JwtDto
import com.api.study.riot_api.domain.dto.LoginRequestDTO
import com.api.study.riot_api.domain.dto.SignupRequestDTO
import com.api.study.riot_api.service.LoginService
import com.api.study.riot_api.service.SignupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
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
        @Param("id") id: String,
        @Param("password") password: String,
    ): JwtDto {
        loginService.execute(LoginRequestDTO(id,password))
        return loginService.execute(LoginRequestDTO(id,password))
    }

    @PostMapping("/signup")
    fun signup(
        @RequestBody @Valid signupRequestDTO: SignupRequestDTO
    ): SignupRequestDTO {
        signupService.save(signupRequestDTO)
        return signupRequestDTO
    }
}