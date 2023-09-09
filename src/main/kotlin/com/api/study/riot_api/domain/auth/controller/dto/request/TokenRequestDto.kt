package com.api.study.riot_api.domain.auth.controller.dto.request

data class TokenRequestDto (
    val accessToken: String,
    val refreshToken: String
)