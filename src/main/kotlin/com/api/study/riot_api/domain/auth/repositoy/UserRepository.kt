package com.api.study.riot_api.domain.auth.repositoy

import com.api.study.riot_api.domain.user.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository:CrudRepository<UserEntity, UUID> {
    fun findById(id: String): Optional<UserEntity>

    fun deleteById(id: String)

}