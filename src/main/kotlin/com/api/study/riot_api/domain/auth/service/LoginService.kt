package com.api.study.riot_api.domain.auth.service

import com.api.study.riot_api.domain.auth.controller.dto.response.TokenResponseDto
import com.api.study.riot_api.domain.auth.controller.dto.response.LoginDto
import com.api.study.riot_api.domain.auth.exception.InvalidPasswordException
import com.api.study.riot_api.domain.auth.exception.NotExistIdException
import com.api.study.riot_api.domain.auth.type.Authority
import com.api.study.riot_api.domain.user.repository.UserRepository
import com.api.study.riot_api.global.security.jwt.JwtProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LoginService(
    private val repository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtProvider: JwtProvider
) {
    fun login(loginData: LoginDto): TokenResponseDto {
        val password: String
        val name: String
        val id: String
        val userId: UUID
        val authority: Authority
        repository.findById(loginData.id).let {
            if(it != null){
                password = it.password
                name = it.name
                id = it.id
                userId = it.userId
                authority = Authority.USER
            } else {
                throw NotExistIdException
            }
        }

        if(!passwordEncoder.matches(loginData.password, password)) throw InvalidPasswordException

        return jwtProvider.receiveToken(userId, authority)

    }
}