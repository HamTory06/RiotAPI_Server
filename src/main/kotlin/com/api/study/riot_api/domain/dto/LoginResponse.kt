package com.api.study.riot_api.domain.dto

data class LoginResponse(
    val data: UserInformationRequestDto,
    val status: String
)
