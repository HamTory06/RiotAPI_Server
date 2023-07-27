package com.api.study.riot_api.domain.entity

import javax.persistence.*

@Entity
class MatchInformation (
    @Id
    val matchId: String,
    val gameCreation: Long,
    val gameDuration: Int,
    val gameEndTimestamp: Long,
    val gameMode: String,
    val gameName: String,
    val gameStartTimestamp: Long,
    val gameType: String,
    val gameVersion: String,
    val mapId: Int,
    val dataVersion: String,
    val puuid0: String?,
    val puuid1: String?,
    val puuid2: String?,
    val puuid3: String?,
    val puuid4: String?,
    val puuid5: String?,
    val puuid6: String?,
    val puuid7: String?,
    val puuid8: String?,
    val puuid9: String?,
)