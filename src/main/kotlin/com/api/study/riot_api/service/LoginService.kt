package com.api.study.riot_api.service

import com.api.study.riot_api.config.SecurityConfig
import com.api.study.riot_api.controller.AccountController
import com.api.study.riot_api.domain.dto.JwtDto
import com.api.study.riot_api.domain.dto.LoginRequestDTO
import com.api.study.riot_api.domain.entity.User
import com.api.study.riot_api.exception.CustomException
import com.api.study.riot_api.exception.ErrorCode
import com.api.study.riot_api.repository.AccountRepository
import com.api.study.riot_api.security.JwtToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginService(
    private var accountRepository: AccountRepository,
    private var securityConfig: SecurityConfig,
    private var jwtToken: JwtToken
) {
    fun execute(requestDTO: LoginRequestDTO): JwtDto {
        val user: User = accountRepository.findById(requestDTO.id)
            .orElseThrow { CustomException(ErrorCode.USER_NOT_FOUND_BAD_REQUEST) }

        if (!securityConfig.passwordEncoder().matches(requestDTO.password, user.password)) {
            throw CustomException(ErrorCode.PASSWORD_NOT_FOUND_BAS_REQUEST)
        }

        return JwtDto(jwtToken.makeJwtToken(user.idx, user.mail))
    }

}