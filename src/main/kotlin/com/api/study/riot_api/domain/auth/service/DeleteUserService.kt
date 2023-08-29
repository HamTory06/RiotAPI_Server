package com.api.study.riot_api.domain.auth.service

import com.api.study.riot_api.domain.auth.repositoy.UserRepository
import org.springframework.stereotype.Service

@Service
class DeleteUserService(
    private val userRepository: UserRepository
) {
    fun deleteUser(id: String){

        val uuid = userRepository.findById(id).orElseThrow{
            throw TODO("id가 존재하지 않을때 예외처리")
        }.uuid
        println(uuid)
//        userRepository.deleteById(id)

    }

}