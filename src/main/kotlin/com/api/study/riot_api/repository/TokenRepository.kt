package com.api.study.riot_api.repository

import com.api.study.riot_api.domain.entity.Token
import com.api.study.riot_api.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TokenRepository: JpaRepository<Token, Long> {

}