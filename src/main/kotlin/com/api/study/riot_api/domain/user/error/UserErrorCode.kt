package com.api.study.riot_api.domain.user.error

import com.api.study.riot_api.infrastructure.error.custom.CustomErrorProperty
import org.springframework.http.HttpStatus

enum class UserErrorCode(
    private val status: HttpStatus,
    private val message: String
) : CustomErrorProperty {

    ID_NOT_FOUND(HttpStatus.BAD_REQUEST, "Id Not Found")
    ;

    override fun status(): HttpStatus = status
    override fun message(): String = message
}