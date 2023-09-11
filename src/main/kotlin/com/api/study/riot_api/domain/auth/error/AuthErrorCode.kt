package com.api.study.riot_api.domain.auth.error

import com.api.study.riot_api.infrastructure.error.custom.CustomErrorProperty

enum class AuthErrorCode(
    private val status: Int,
    private val message: String
) : CustomErrorProperty {

    INVALID_PASSWORD_EXCEPTION(400, "Invalid Password"),
    ID_EXIST_EXCEPTION(400, "Id Not Exist"),
    ID_NOT_FOUND(400, "Id Not Found")
    ;

    override fun status(): Int = status
    override fun message(): String = message
}