package com.api.study.riot_api.service

import com.api.study.riot_api.api.ExternalKrApiClient
import com.api.study.riot_api.controller.AccountController
import com.api.study.riot_api.domain.dto.riotapi.kr.UserInformationResponse
import com.api.study.riot_api.exception.CustomException
import com.api.study.riot_api.exception.ErrorCode
import com.api.study.riot_api.repository.LolRepository
import com.api.study.riot_api.repository.ValRepository
import com.api.study.riot_api.security.JwtToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RiotAPIService(
    private val lolRepository: LolRepository,
    private val valRepository: ValRepository,
    private val externalKrApiClient: ExternalKrApiClient,
    private val jwtToken: JwtToken
) {
    private val logger: Logger = LoggerFactory.getLogger(AccountController::class.java)
    fun getUserInformation(apiKey: String, userName: String, accessToken: String): Any {
        return if(!jwtToken.validateToken(accessToken)){
            logger.info(externalKrApiClient.getUserInformationName(apiKey,userName).name)
            externalKrApiClient.getUserInformationName(apiKey, userName)
        } else {
            CustomException(ErrorCode.TOKEN_NOT_FOUND_FORBIDDEN_ERROR)
        }
    }
}