package com.api.study.riot_api.controller

import com.api.study.riot_api.domain.dto.LoginRequestDto
import com.api.study.riot_api.domain.dto.LoginResponse
import com.api.study.riot_api.domain.dto.SameIdDto
import com.api.study.riot_api.domain.dto.SignupRequestDto
import com.api.study.riot_api.repository.AccountRepository
import com.api.study.riot_api.service.JwtService
import com.api.study.riot_api.service.LoginService
import com.api.study.riot_api.service.SignupService
import com.api.study.riot_api.service.UserInformationService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
class AccountController(
    private var accountRepository: AccountRepository,
    private var signupService: SignupService,
    private var loginService: LoginService,
    private var jwtService: JwtService,
    private var userInformationService: UserInformationService
) {

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid loginRequestDTO: LoginRequestDto,
    ): LoginResponse {
        return LoginResponse(loginService.execute(loginRequestDTO), "200")
    }

    @PostMapping("/signup")
    fun signup(
        @RequestBody @Valid signupRequestDTO: SignupRequestDto
    ): SignupRequestDto {
        signupService.save(signupRequestDTO)
        return signupRequestDTO
    }

    @GetMapping("/check/sameId")
    fun checkSameId(
        @RequestParam("id") id: String
    ): SameIdDto {
        return SameIdDto(accountRepository.findById(id).isPresent)
    }


    @DeleteMapping("/delete-user/{id}")
    fun deleteUser(
        @RequestHeader("Authorization") accessToken: String,
        @PathVariable("id") id: String
    ) {
        userInformationService.deleteUser(id, accessToken)
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestHeader("Authorization") refreshToken: String
    ) {
        jwtService.refresh(refreshToken)
    }
}