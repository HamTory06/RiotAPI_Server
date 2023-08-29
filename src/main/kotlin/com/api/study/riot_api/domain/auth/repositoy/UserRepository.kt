package com.api.study.riot_api.domain.auth.repositoy

import com.api.study.riot_api.domain.auth.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository:JpaRepository<UserEntity, UUID> {
    fun findById(id: String): Optional<UserEntity>

    fun deleteById(id: String)

}