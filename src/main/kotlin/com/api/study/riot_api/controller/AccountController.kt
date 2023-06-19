package com.api.study.riot_api.controller

import com.api.study.riot_api.domain.dto.JwtDto
import com.api.study.riot_api.domain.dto.LoginRequestDTO
import com.api.study.riot_api.domain.dto.LoginResponse
import com.api.study.riot_api.domain.dto.SignupRequestDto
import com.api.study.riot_api.service.JwtService
import com.api.study.riot_api.service.LoginService
import com.api.study.riot_api.service.SignupService
import com.api.study.riot_api.service.UserInformationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/account")
class AccountController {
    @Autowired
    private lateinit var signupService: SignupService
    @Autowired
    private lateinit var loginService: LoginService
    @Autowired
    private lateinit var jwtService: JwtService
    @Autowired
    private lateinit var userInformationService: UserInformationService

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid loginRequestDTO: LoginRequestDTO,
    ): LoginResponse {
        loginService.execute(loginRequestDTO)
        return LoginResponse(loginService.execute(loginRequestDTO), "200")
    }

    @PostMapping("/signup")
    fun signup(
        @RequestBody @Valid signupRequestDTO: SignupRequestDto
    ): SignupRequestDto {
        signupService.save(signupRequestDTO)
        return signupRequestDTO
    }

    @DeleteMapping("/delete-user/{idx}")
    fun deleteUser(
        @RequestHeader("Authorization") accessToken: String,
        @PathVariable("idx") idx: Long
    ){
        userInformationService.deleteUser(idx, accessToken)
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestHeader("Authorization") refreshToken: String
    ){
        jwtService.refresh(refreshToken)
    }
}