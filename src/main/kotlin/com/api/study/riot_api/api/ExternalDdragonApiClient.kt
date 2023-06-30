package com.api.study.riot_api.api

import org.springframework.cloud.openfeign.FeignClient


@FeignClient(name = "ddragon", url = "https://ddragon.api.riotgames.com/", configuration = [])
interface ExternalDdragonApiClient {

}