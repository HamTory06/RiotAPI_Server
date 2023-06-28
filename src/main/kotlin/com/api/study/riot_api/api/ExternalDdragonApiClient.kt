package com.api.study.riot_api.api

import com.api.study.riot_api.domain.dto.riotapi.ddragon.LolVersionsResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping


@FeignClient(name = "ddragon", url = "https://ddragon.api.riotgames.com/", configuration = [])
interface ExternalDdragonApiClient {
    @GetMapping("/api/versions.json")
    fun getLolVersions(): LolVersionsResponse

}