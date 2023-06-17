package com.api.study.riot_api.service

import com.api.study.riot_api.controller.AccountController
import com.api.study.riot_api.domain.dto.SignupRequestDTO
import com.api.study.riot_api.domain.entity.User
import com.api.study.riot_api.exception.CustomException
import com.api.study.riot_api.exception.ErrorCode
import com.api.study.riot_api.repository.AccountRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignupService(private val accountRepository: AccountRepository) {
    private val LOGGER: Logger = LoggerFactory.getLogger(AccountController::class.java)
    private val passwordEncoder = BCryptPasswordEncoder()

    private fun signupService(request: SignupRequestDTO) {
        val encryptedPassword = passwordEncoder.encode(request.password)

        val user = User(
            name = request.name,
            id = request.id,
            password = encryptedPassword,
            mail = request.mail,
            lolName = request.lolName,
            valName = request.valName
        );

        accountRepository.save(user)
    }

    fun save(signupRequestDTO: SignupRequestDTO){
         if(isContainsHangul(signupRequestDTO.name)||isContainsHangul(signupRequestDTO.id)){
            throw CustomException(ErrorCode.NOT_HANGEUL_INTERNAL_SERVER_ERROR)
            LOGGER.info("영어와 숫자만 입력 해주세요.")
        }
        signupService(signupRequestDTO)
    }

    private fun isContainsHangul(value: String): Boolean{
        val regex = Regex("[a-zA-Z0-9]+")
        return !regex.containsMatchIn(value)
    }
}