package com.api.study.riot_api.controller

import com.api.study.riot_api.domain.dto.riotapi.LolVersion
import com.api.study.riot_api.domain.dto.riotapi.kr.ChampionMasteryDto
import com.api.study.riot_api.domain.dto.riotapi.kr.ChampionMasteryDtoArray
import com.api.study.riot_api.domain.entity.LolUser
import com.api.study.riot_api.domain.entity.MatchInformation
import com.api.study.riot_api.service.RiotAPIService
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/riotAPI")
class RiotController(
    private var riotAPIService: RiotAPIService
) {
    @Value("\${app.apiKey}")
    private val riotAPIKey: String = ""
    private val logger: Logger = LoggerFactory.getLogger(RiotController::class.java)

    @GetMapping("/riot.txt")
    fun download(): ResponseEntity<ByteArray> {
        val file =
            File("/Users/hamtory/Documents/github/BackEnd/RiotAPI/src/main/kotlin/com/api/study/riot_api/file/riot.txt")
        val path = Paths.get(file.absolutePath)

        val header = HttpHeaders()
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=riot.txt")
        header.add("Cache-Control", "no-cache, no-store, must-revalidate")
        header.add("Pragma", "no-cache")
        header.add("Expires", "0")

        return ResponseEntity.ok()
            .headers(header)
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(Files.readAllBytes(path));
    }

    @GetMapping("/lol/by-name/{username}")
    fun getUserPuuIdName(
        @PathVariable("username") username: String
    ): LolUser {
        return riotAPIService.addLolUser(username)
    }

    @GetMapping("/lol/match/getMatchId/{puuid}")
    fun getMatchId(
        @PathVariable("puuid") puuid: String,
        @RequestParam("start") start: Int,
        @RequestParam("count") count: Int
    ): List<String>{
        return riotAPIService.getMatchId(start, count, puuid)
    }

    @GetMapping("/lol/match/getMatchInformation/{matchId}")
    fun getMatchInformation(
        @PathVariable("matchId") matchId: String,
        @RequestParam("puuid") puuid: String,
    ): MatchInformation {
        return riotAPIService.getMatchInformation(matchId,puuid)
    }

    @GetMapping("/lol/champion/masteries/{champion}/{lolUserName}")
    fun getChampionMasteries(
        @PathVariable("lolUserName") lolUserName: String,
        @PathVariable("champion") champion: String
    ): ChampionMasteryDto {
        return riotAPIService.getUserChampionMasteries(lolUserName, champion)
    }

    @GetMapping("lol/champion/masteries/{lolUserName}")
    fun getChampionMasteries(
        @PathVariable("lolUserName") lolUserName: String,
    ): ChampionMasteryDtoArray {
        return riotAPIService.getUserChampionMasteries(lolUserName)
    }

    @GetMapping("/lol/status/versions")
    fun getVersions(): LolVersion {
        return LolVersion(riotAPIService.getVersions())
    }

}