package com.api.study.riot_api.controller

import com.api.study.riot_api.service.LoginService
import com.api.study.riot_api.service.RiotAPIService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@RequestMapping("/api/riotAPI")
class RiotController {
    @Value("\${app.apiKey}")
    private val riotAPIKey: String = ""

    @Autowired
    private lateinit var riotAPIService: RiotAPIService

    private val logger: Logger = LoggerFactory.getLogger(LoginService::class.java)


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
        @RequestParam("accessToken") accessToken: String,
        @PathVariable("username") username: String,
    ) {
        riotAPIService.getUserInformation(riotAPIKey, username, accessToken)
    }

}