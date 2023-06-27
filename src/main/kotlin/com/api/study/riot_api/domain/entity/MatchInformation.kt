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
    val dataVersion: String
)