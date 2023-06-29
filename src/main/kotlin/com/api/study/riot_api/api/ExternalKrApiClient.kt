package com.api.study.riot_api.api

import com.api.study.riot_api.domain.dto.riotapi.kr.LolUserInformationResponse
import com.api.study.riot_api.exception.FeignCustomException
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "kr", url = "https://kr.api.riotgames.com/",configuration = [FeignCustomException::class])
interface ExternalKrApiClient {

    @GetMapping("lol/summoner/v4/summoners/by-name/{username}")
    fun getUserInformationName(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("username") username: String
    ): LolUserInformationResponse

    @GetMapping("lol/summoner/v4/summoners/by-account/{accountid}")
    fun getUserInformationAccountId(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("accountid") accountId: String
    ): LolUserInformationResponse

    @GetMapping("lol/summoner/v4/summoners/by-puuid/{PUUID}")
    fun getUserInformationPuuId(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("PUUID") puuId: String,
    ): LolUserInformationResponse

}