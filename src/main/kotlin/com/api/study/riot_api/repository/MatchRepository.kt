package com.api.study.riot_api.repository

import com.api.study.riot_api.domain.entity.MatchInformation
import org.springframework.data.jpa.repository.JpaRepository

interface MatchRepository: JpaRepository<MatchInformation, String> {

}