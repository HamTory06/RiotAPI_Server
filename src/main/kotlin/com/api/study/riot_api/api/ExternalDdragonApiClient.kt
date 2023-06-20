package com.api.study.riot_api.api

import com.api.study.riot_api.data.network.retrofit.lol.response.user_matches_response.UserMatchesResponse
import com.api.study.riot_api.domain.dto.riotapi.asiar.UserMatchesIdResponse
import com.api.study.riot_api.domain.dto.riotapi.ddragon.LolVersionsResponse
import com.api.study.riot_api.domain.dto.riotapi.kr.UserInformationResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "ddragon", url = "https://ddragon.api.riotgames.com/")
interface ExternalDdragonApiClient {
    @GetMapping("/api/versions.json")
    fun getLolVersions(): LolVersionsResponse

    @GetMapping("/lol/match/v5/matches/by-puuid/{PUUID}/ids")
    fun getUserMatchesId(
        @PathVariable(value = "PUUID") puuid: String,
        @RequestParam(value = "api_key") apiKey: String,
        @RequestParam(value = "start") start: Int,
        @RequestParam(value = "count") count: Int,
    ): UserMatchesIdResponse

    @GetMapping("/lol/match/v5/matches/{matchId}")
    fun getUserMatches(
        @RequestParam(value = "api_key") apiKey: String,
        @PathVariable(value = "matchId") matchId: String
    ): UserMatchesResponse

}