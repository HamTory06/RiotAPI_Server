package com.api.study.riot_api.domain.dto

import javax.validation.constraints.NotBlank

data class LoginRequestDto(
    @field:NotBlank(message = "id가 비어있습니다")
    val id: String,
    @field:NotBlank(message = "password가 비어있습니다")
    val password: String
)
