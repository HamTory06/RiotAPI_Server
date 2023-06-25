package com.api.study.riot_api.api

import com.api.study.riot_api.domain.dto.riotapi.asiar.RiotUserPuuIdResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "asia", url = "https://asia.api.riotgames.com/")
interface ExternalAsiaApiClient {
    @GetMapping("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    fun getUserPuuIdName(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("gameName") gameName: String,
        @PathVariable("tagLine") tagLine: String
    ): RiotUserPuuIdResponse

    @GetMapping("/riot/account/v1/accounts/by-puuid/{puuid}")
    fun getUserPuuId(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("puuid") puuId: String
    ): RiotUserPuuIdResponse

}