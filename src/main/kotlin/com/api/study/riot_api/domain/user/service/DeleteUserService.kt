package com.api.study.riot_api.domain.user.service

import com.api.study.riot_api.domain.user.repository.UserRepository
import com.api.study.riot_api.global.error.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteUserService(
    private val userRepository: UserRepository
) {
    @Transactional
    fun deleteUser(id: String, token: String){
        userRepository.findById(id).let {
            if(it != null){
                userRepository.deleteById(it.id)
            } else {
               throw TODO("존재하지 않는 id삭제 요청")
            }
        }
    }

}