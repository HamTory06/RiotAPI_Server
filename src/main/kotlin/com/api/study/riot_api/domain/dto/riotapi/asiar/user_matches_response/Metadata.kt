package com.api.study.riot_api.domain.dto.riotapi.asiar.user_matches_response

data class Metadata(
    val dataVersion: String,
    val matchId: String,
    val participants: List<String>
)