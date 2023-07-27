package com.api.study.riot_api.api

import com.api.study.riot_api.domain.dto.riotapi.ddragon.champion_information.ChampionInformationDataDto
import com.api.study.riot_api.domain.dto.riotapi.ddragon.summoner.SummonerResponseDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@FeignClient(name = "ddragon", url = "https://ddragon.api.riotgames.com/", configuration = [])
interface ExternalDdragonApiClient {
    @GetMapping("cdn/{version}/data/ko_KR/summoner.json")
    fun getSummoner(@PathVariable("version") version: String): SummonerResponseDto

    @GetMapping("cdn/{version}/data/ko_KR/champion.json")
    fun getChampion(@PathVariable("version") version: String): ChampionInformationDataDto

}