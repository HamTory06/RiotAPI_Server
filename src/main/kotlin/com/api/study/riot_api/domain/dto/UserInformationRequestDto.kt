package com.api.study.riot_api.domain.dto

data class UserInformationRequestDto(
    val idx: Long,
    val id: String,
    val name: String,
    val mail: String,
    val lolUserId: Long?,
    val valUserId: Long?,
    val token: JwtDto
)
