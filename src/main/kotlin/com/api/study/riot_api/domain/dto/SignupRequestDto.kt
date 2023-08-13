package com.api.study.riot_api.domain.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size


data class SignupRequestDto(
    @field:NotBlank(message = "이름은 필수 입력 값입니다.")
    @field:Size(max = 16)
    val name: String,
    @field:NotBlank(message = "ID는 필수 입력 값입니다.")
    @field:Size(message = "아이디는 1글자 이상, 16글자 이하입니다.", min = 1, max = 16)
    val id: String,
    @field:NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @field:Size(message = "비밀번호는 8글자 이상, 50글자 이하입니다.", min = 8, max = 50)
    val password: String
)