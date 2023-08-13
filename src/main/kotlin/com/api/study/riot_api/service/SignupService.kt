package com.api.study.riot_api.service

import com.api.study.riot_api.domain.dto.SameIdDto
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
    private val passwordPattern = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]+\$")

    private fun signupService(request: SignupRequestDto) {
        val encryptedPassword = passwordEncoder.encode(request.password)

        if(passwordPattern.matches(request.password)){
            val userEntity = User(
                name = request.name,
                id = request.id,
                password = encryptedPassword,
            )
            accountRepository.save(userEntity)
        } else {
            throw CustomException(ErrorCode.PASSWORD_BAD_REQUEST)
        }



    }

    fun save(signupRequestDTO: SignupRequestDto) {
        if (isContainsHangul(signupRequestDTO.id)) {
            throw CustomException(ErrorCode.NOT_HANGEUL_INTERNAL_SERVER_ERROR)
        }
        signupService(signupRequestDTO)
    }

    private fun isContainsHangul(value: String): Boolean {
        val regex = Regex("[a-zA-Z0-9]+")
        return !regex.containsMatchIn(value)
    }

    fun same(id: String): SameIdDto {
        if(id.isEmpty()){
            throw CustomException(ErrorCode.EMPTY_TEXT_BAD_REQUEST)
        } else {
            return SameIdDto(accountRepository.findById(id).isPresent)
        }

    }
}