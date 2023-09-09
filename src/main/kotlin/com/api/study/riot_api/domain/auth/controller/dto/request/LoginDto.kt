package com.api.study.riot_api.domain.auth.controller.dto.request

data class LoginDto (
    val id: String,
    val name: String,
    val token: TokenRequestDto
)