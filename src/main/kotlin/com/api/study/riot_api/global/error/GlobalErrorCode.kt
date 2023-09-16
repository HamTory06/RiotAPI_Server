package com.api.study.riot_api.global.error

import com.api.study.riot_api.infrastructure.error.custom.CustomErrorProperty
import org.springframework.http.HttpStatus

enum class GlobalErrorCode(
    private val status: HttpStatus,
    private val message: String
) : CustomErrorProperty {
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid Token"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Expired Token"),
    UNEXPECTED_TOKEN(HttpStatus.UNAUTHORIZED, "Unexpected Token"),
    NOT_EXIST_USER(HttpStatus.BAD_REQUEST,"Not Exist User"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");

    override fun status(): HttpStatus = status
    override fun message(): String = message
}