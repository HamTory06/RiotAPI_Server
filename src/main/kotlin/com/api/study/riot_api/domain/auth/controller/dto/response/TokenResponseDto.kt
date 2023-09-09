package com.api.study.riot_api.domain.auth.controller.dto.response

import com.api.study.riot_api.domain.auth.type.Authority
import java.time.LocalDateTime

data class TokenResponseDto(
    val accessToken: String,
    val expiredAt: LocalDateTime,
    val refreshToken: String,
    val authority: Authority?
)
