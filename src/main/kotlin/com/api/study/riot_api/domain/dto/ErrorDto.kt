package com.api.study.riot_api.domain.dto

import lombok.Getter

@Getter
data class ErrorDto(
    val status: Int,
    val message: String
)