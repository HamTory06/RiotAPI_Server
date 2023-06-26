package com.api.study.riot_api.api

import com.api.study.riot_api.data.network.retrofit.lol.response.user_matches_response.UserMatchesResponse
import com.api.study.riot_api.domain.dto.riotapi.asiar.RiotUserPuuIdResponse
import com.api.study.riot_api.domain.dto.riotapi.asiar.UserMatchesIdResponse
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

    @GetMapping("/riot/account/v1/accounts/by-puuid/{puuId}")
    fun getUserPuuId(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("puuId") puuId: String
    ): RiotUserPuuIdResponse

    @GetMapping("/lol/match/v5/matches/by-puuid/{puuId}/ids")
    fun getMatchId(
        @RequestParam("api_key") apiKey: String,
        @PathVariable("puuId") puuId: String,
//        @RequestParam("startTime") startTime: Long,
//        @RequestParam("endTime") endTime: Long,
//        @RequestParam("queue") queue: Int,
//        @RequestParam("type") type: String,
        @RequestParam("start") start: Int,
        @RequestParam("count") count: Int
    ): UserMatchesIdResponse

    @GetMapping("/lol/match/v5/matches/{matchId}")
    fun getUserMatches(
        @RequestParam(value = "api_key") apiKey: String,
        @PathVariable(value = "matchId") matchId: String
    ): UserMatchesResponse

}