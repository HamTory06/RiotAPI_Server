package com.api.study.riot_api.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ChampionMastery (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long = 0L,
    var championId: Long? = null,
    var puuid: String? = null,
    var championLevel: Int? = null,
    var championPoints: Int? = null,
    var lastPlayTime: Long? = null,
    var championPointsSinceLastLevel: Long? = null,
    var championPointsUntilNextLevel: Long? = null,
    var chestGranted: Boolean? = null,
    var tokensEarned: Int? = null,
    var summonerId: String? = null
)