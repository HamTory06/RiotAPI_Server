package com.api.study.riot_api.global.principle

import com.api.study.riot_api.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomManagerDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByIdOrNull(UUID.fromString(username)) ?: TODO("예외 처리")
        return CustomUserDetails(user.userId)
    }

}