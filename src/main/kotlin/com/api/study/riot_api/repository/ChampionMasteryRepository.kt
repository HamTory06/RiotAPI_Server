package com.api.study.riot_api.repository

import com.api.study.riot_api.domain.entity.ChampionMastery
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ChampionMasteryRepository: JpaRepository<ChampionMastery, Long> {
    fun findByChampionId(championId: Long): Optional<ChampionMastery>
}