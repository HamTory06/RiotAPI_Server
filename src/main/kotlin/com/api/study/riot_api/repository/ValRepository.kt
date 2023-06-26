package com.api.study.riot_api.repository

import com.api.study.riot_api.domain.entity.ValUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ValRepository: JpaRepository<ValUser, Long> {
    fun findByValUserName(valUserName: String): Optional<ValUser>
}