package com.api.study.riot_api.domain.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ParticipantsArenaUserPuuid (
    @Id
    val matchId: String,
    val puuid_0: String,
    val puuid_1: String,
    val puuid_2: String,
    val puuid_3: String,
    val puuid_4: String,
    val puuid_5: String,
    val puuid_6: String,
    val puuid_7: String,
)