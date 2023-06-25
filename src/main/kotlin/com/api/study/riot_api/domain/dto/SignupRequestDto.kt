package com.api.study.riot_api.domain.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size


data class SignupRequestDto(
    @field:NotBlank(message = "이름은 필수 입력 값입니다.")
    @field:Size(max = 16)
    val name: String,
    @field:NotBlank(message = "ID는 필수 입력 값입니다.")
    @field:Size(max = 16)
    val id: String,
    @field:NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @field:Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{1,}\$",message = "대소문자, 특수문자, 숫자를 포함 시켜주세요.")
    @field:Size(message = "비밀번호는 8글자 이상, 20글자 이하입니다.", min = 8, max = 50)
    val password: String,
    @field:Email
    val mail: String
)