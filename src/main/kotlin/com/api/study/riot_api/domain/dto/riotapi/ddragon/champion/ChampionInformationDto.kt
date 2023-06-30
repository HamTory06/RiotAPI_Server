package com.api.study.riot_api.domain.dto.riotapi.ddragon.champion

data class ChampionInformationDto(
    val type: String,
    val format: String,
    val version: String,
    val data: Map<String, Data>
)