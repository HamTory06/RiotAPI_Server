package com.api.study.riot_api.domain.dto.riotapi.kr

data class UserInformationResponse(
    val accountId: String,
    val id: String,
    val name: String,
    val profileIconId: Int,
    val puuid: String,
    val revisionDate: Long,
    val summonerLevel: Int
)