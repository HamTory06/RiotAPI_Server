package com.api.study.riot_api.domain.user.repository

import com.api.study.riot_api.domain.user.UserEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository:CrudRepository<UserEntity, UUID> {
    fun findById(id: String): UserEntity?

    fun deleteById(id: String)

}