package com.api.study.riot_api.domain.dto.riotapi.ddragon.champion_information

data class ChampionInformationDataDto(
    val `data`: Data,
    val format: String,
    val type: String,
    val version: String
)