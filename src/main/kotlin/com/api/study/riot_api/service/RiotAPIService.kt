package com.api.study.riot_api.service

import com.api.study.riot_api.api.ExternalAsiaApiClient
import com.api.study.riot_api.api.ExternalKrApiClient
import com.api.study.riot_api.controller.AccountController
import com.api.study.riot_api.data.network.retrofit.lol.response.user_matches_response.UserMatchesResponse
import com.api.study.riot_api.domain.dto.riotapi.kr.UserInformationResponse
import com.api.study.riot_api.exception.CustomException
import com.api.study.riot_api.exception.ErrorCode
import com.api.study.riot_api.security.JwtToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RiotAPIService(
    private val externalKrApiClient: ExternalKrApiClient,
) {
    private val logger: Logger = LoggerFactory.getLogger(AccountController::class.java)

    @Autowired
    private lateinit var externalAsiaApiClient: ExternalAsiaApiClient

    @Value("\${app.apiKey}")
    private val apiKey = ""
    fun getUserInformation(apiKey: String, userName: String, accessToken: String): UserInformationResponse {
        val userInformationResponse = externalKrApiClient.getUserInformationName(apiKey, userName)
        logger.info(externalKrApiClient.getUserInformationName(apiKey, userName).name)
        return userInformationResponse
    }

    fun getMatchId(puuId: String, start: Int, count: Int): List<String> {
        return externalAsiaApiClient.getMatchId(
            apiKey = apiKey,
            puuId = puuId,
            start = 0,
            count = 10
        )
    }

    fun getMatchInformation(matchId: String): UserMatchesResponse {
        return externalAsiaApiClient.getUserMatches(apiKey, matchId)
    }
}