package com.api.study.riot_api.api

import com.api.study.riot_api.domain.dto.riotapi.ddragon.verison.LolVersionsResponse
import com.api.study.riot_api.domain.dto.riotapi.ddragon.champion.ChampionInformationDto
import com.api.study.riot_api.exception.FeignCustomException
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "leagueoflegends", url = "https://ddragon.leagueoflegends.com/",configuration = [FeignCustomException::class])
interface ExternalKrDdragonLeagueoflegendsApiClient {
    @GetMapping("api/versions.json")
    fun getVersions(): LolVersionsResponse

    @GetMapping("/cdn/{version}/data/ko_KR/champion/{champion}")
    fun getChampionInformation(
        @PathVariable("champion") champion: String,
        @PathVariable("version") version: String
    ): ChampionInformationDto
}