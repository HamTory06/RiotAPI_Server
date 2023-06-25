package com.api.study.riot_api.service

import com.api.study.riot_api.api.ExternalKrApiClient
import com.api.study.riot_api.controller.AccountController
import com.api.study.riot_api.domain.dto.riotapi.kr.UserInformationResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RiotAPIService(
    private val externalKrApiClient: ExternalKrApiClient,
) {
    private val logger: Logger = LoggerFactory.getLogger(AccountController::class.java)
    fun getUserInformation(apiKey: String, userName: String, accessToken: String): UserInformationResponse {
        val userInformationResponse = externalKrApiClient.getUserInformationName(apiKey, userName)
        logger.info(externalKrApiClient.getUserInformationName(apiKey,userName).name)
        return userInformationResponse
    }
}