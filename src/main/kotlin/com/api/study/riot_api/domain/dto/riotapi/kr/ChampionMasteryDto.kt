package com.api.study.riot_api.domain.dto.riotapi.kr

data class ChampionMasteryDto(
    val puuid: String,
    val championId: Long,
    val championLevel: Int,
    val championPoints: Int,
    val lastPlayTime: Long,
    val championPointsSinceLastLevel: Long,
    val championPointsUntilNextLevel: Long,
    val chestGranted: Boolean,
    val tokensEarned: Int,
    val summonerId: String
)
