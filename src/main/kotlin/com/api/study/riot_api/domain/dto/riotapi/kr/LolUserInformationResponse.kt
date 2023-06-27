package com.api.study.riot_api.domain.dto.riotapi.kr

data class LolUserInformationResponse(
    val accountId: String,
    val id: String,
    val name: String,
    val profileIconId: Int,
    val puuid: String,
    val revisionDate: Long,
    val summonerLevel: Int
)