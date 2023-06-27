package com.api.study.riot_api.service

import com.api.study.riot_api.api.ExternalAsiaApiClient
import com.api.study.riot_api.api.ExternalKrApiClient
import com.api.study.riot_api.domain.dto.riotapi.kr.LolUserInformationResponse
import com.api.study.riot_api.domain.entity.MatchInformation
import com.api.study.riot_api.repository.LolRepository
import com.api.study.riot_api.repository.MatchRepository
import com.api.study.riot_api.repository.ValRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RiotAPIService(
    private val userInformationService: UserInformationService,
    private val externalKrApiClient: ExternalKrApiClient,
    private val lolRepository: LolRepository,
    private val valRepository: ValRepository,
    private val matchRepository: MatchRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(RiotAPIService::class.java)

    @Autowired
    private lateinit var externalAsiaApiClient: ExternalAsiaApiClient

    @Value("\${app.apiKey}")
    private val apiKey = ""
    fun getLolUserInformation(apiKey: String, userName: String): LolUserInformationResponse {

        if (!lolRepository.findByLolUserName(userName).isPresent) {
            userInformationService.addLolUser(userName)
        }

        return externalKrApiClient.getUserInformationName(apiKey, userName)
    }

    fun getValUserInformation(apiKey: String, userName: String){
        if(!valRepository.findByValUserName(userName).isPresent){
            userInformationService.addValUser(userName)
        }
    }

    fun getMatchId(puuId: String, start: Int, count: Int): List<String> {
        return externalAsiaApiClient.getMatchId(
            apiKey = apiKey,
            puuId = puuId,
            start = 0,
            count = 10
        )
    }

    fun getMatchInformation(matchId: String): MatchInformation {
        if(!matchRepository.findById(matchId).isPresent) {
            val matchInformationData = externalAsiaApiClient.getUserMatches(apiKey, matchId)
            return MatchInformation(
                matchId = matchInformationData.metadata.matchId,
                dataVersion = matchInformationData.metadata.dataVersion,
                gameName = matchInformationData.info.gameName,
                gameCreation = matchInformationData.info.gameCreation,
                gameDuration = matchInformationData.info.gameDuration,
                gameEndTimestamp = matchInformationData.info.gameEndTimestamp,
                gameStartTimestamp = matchInformationData.info.gameStartTimestamp,
                gameMode = matchInformationData.info.gameMode,
                gameType = matchInformationData.info.gameType,
                gameVersion = matchInformationData.info.gameVersion,
                mapId = matchInformationData.info.mapId
            )
        } else {
            return matchRepository.findById(matchId).get()
        }
    }
}