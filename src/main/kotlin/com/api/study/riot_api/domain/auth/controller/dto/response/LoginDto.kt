package com.api.study.riot_api.domain.auth.controller.dto.response

import javax.validation.constraints.NotBlank


data class LoginDto(
    val id: String,
    val password: String
)
