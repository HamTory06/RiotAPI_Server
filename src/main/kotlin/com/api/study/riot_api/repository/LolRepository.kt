package com.api.study.riot_api.repository

import com.api.study.riot_api.domain.entity.LolUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface LolRepository: JpaRepository<LolUser, Long> {
    fun findByLolUserName(lolUserName: String): Optional<LolUser>

}