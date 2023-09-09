package com.api.study.riot_api.domain.auth.service

import com.api.study.riot_api.domain.auth.controller.dto.response.SignupDto
import com.api.study.riot_api.domain.user.UserEntity
import com.api.study.riot_api.domain.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class SignupService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun signup(signupDto: SignupDto){
        if(userRepository.findById(signupDto.id) != null)
            throw TODO("이미 존재하는 ID로 회원가입")

        val userEntity = UserEntity(
            userId = UUID.randomUUID(),
            id = signupDto.id,
            password = signupDto.password,
            name = signupDto.name,
        )
        userEntity.hashPassword(passwordEncoder)
        userRepository.save(userEntity)
    }
}