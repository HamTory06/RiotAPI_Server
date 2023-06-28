package com.api.study.riot_api.service

import com.api.study.riot_api.domain.dto.SignupRequestDto
import com.api.study.riot_api.domain.entity.User
import com.api.study.riot_api.exception.CustomException
import com.api.study.riot_api.exception.ErrorCode
import com.api.study.riot_api.repository.AccountRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignupService(
    private val accountRepository: AccountRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(SignupService::class.java)
    private val passwordEncoder = BCryptPasswordEncoder()

    private fun signupService(request: SignupRequestDto) {
        val encryptedPassword = passwordEncoder.encode(request.password)

        val userEntity = User(
            name = request.name,
            id = request.id,
            password = encryptedPassword,
        )

        accountRepository.save(userEntity)
    }

    fun save(signupRequestDTO: SignupRequestDto) {
        if (isContainsHangul(signupRequestDTO.name) || isContainsHangul(signupRequestDTO.id)) {
            throw CustomException(ErrorCode.NOT_HANGEUL_INTERNAL_SERVER_ERROR)
        }
        signupService(signupRequestDTO)
    }

    private fun isContainsHangul(value: String): Boolean {
        val regex = Regex("[a-zA-Z0-9]+")
        return !regex.containsMatchIn(value)
    }
}