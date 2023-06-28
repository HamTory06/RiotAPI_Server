package com.api.study.riot_api.repository

import com.api.study.riot_api.domain.entity.Challenges
import org.springframework.data.jpa.repository.JpaRepository

interface ChallengesRepository: JpaRepository<Challenges, Long> {

}