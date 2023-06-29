package com.api.study.riot_api.service

import com.api.study.riot_api.controller.AccountController
import com.api.study.riot_api.exception.CustomException
import com.api.study.riot_api.exception.ErrorCode
import com.api.study.riot_api.security.JwtToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class JwtService(
    private val jwtToken: JwtToken,
) {

    private val logger: Logger = LoggerFactory.getLogger(AccountController::class.java)

    @Value("\${app.secretKey}")
    private val secret: String = ""
    fun refresh(refreshToken: String): String{
        if(jwtToken.validateToken(refreshToken)){
            throw CustomException(ErrorCode.TOKEN_NOT_FOUND_FORBIDDEN_ERROR)
        }
        return jwtToken.makeJwtRefreshToken()
    }
}