package com.api.study.riot_api.api

import com.api.study.riot_api.domain.dto.riotapi.ddragon.champion.ChampionInformationDto
import com.api.study.riot_api.domain.dto.riotapi.kr.ChampionMasteryDto
import com.api.study.riot_api.domain.dto.riotapi.kr.ChampionMasteryDtoArray
import com.api.study.riot_api.domain.dto.riotapi.kr.LolUserInformationResponse
import com.api.study.riot_api.domain.dto.riotapi.kr.LolUserInformationUpdateResponse
import com.api.study.riot_api.exception.FeignCustomException
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "kr", url = "https://kr.api.riotgames.com/",configuration = [FeignCustomException::class])
interface ExternalKrApiClient {

    @GetMapping("lol/summoner/v4/summoners/by-name/{username}", consumes = ["application/json;charset=utf-8"])
    fun getUserInformationName(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("username") username: String
    ): LolUserInformationResponse

    @GetMapping("lol/summoner/v4/summoners/by-name/{username}", consumes = ["application/json;charset=utf-8"])
    fun getUserUpdateInformationName(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("username") username: String
    ): LolUserInformationUpdateResponse

    @GetMapping("lol/summoner/v4/summoners/by-account/{accountId}", consumes = ["application/json;charset=utf-8"])
    fun getUserInformationAccountId(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("accountId") accountId: String
    ): LolUserInformationResponse

    @GetMapping("lol/summoner/v4/summoners/by-puuid/{PUUID}", consumes = ["application/json;charset=utf-8"])
    fun getUserInformationPuuId(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("PUUID") puuId: String,
    ): LolUserInformationResponse

    @GetMapping("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}/top", consumes = ["application/json;charset=utf-8"])
    fun getUserChampionMasteries(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("encryptedSummonerId") lolUserId: String,
        @RequestParam("count") count: Int
    ): ChampionMasteryDtoArray

    @GetMapping("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}/by-champion/{championId}", consumes = ["application/json;charset=utf-8"])
    fun getUserChampionMasteries(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("encryptedSummonerId") lolUserId: String,
        @PathVariable("championId") championId: String,
    ): ChampionMasteryDto

    @GetMapping("/lol/static-data/v3/champions", consumes = ["application/json;charset=utf-8"])
    fun getChampionData(
        @RequestParam("api_key") apiKey: String,
    ): ChampionInformationDto


}