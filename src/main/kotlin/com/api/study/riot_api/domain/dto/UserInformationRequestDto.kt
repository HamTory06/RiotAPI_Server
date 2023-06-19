package com.api.study.riot_api.domain.dto

data class UserInformationRequestDto(
    val idx: Long,
    val id: String,
    val name: String,
    val mail: String,
    val lolUserName: String?,
    val valUserName: String?,
    val token: JwtDto
)
