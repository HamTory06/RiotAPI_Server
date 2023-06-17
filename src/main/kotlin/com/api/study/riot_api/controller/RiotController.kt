package com.api.study.riot_api.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@RestController
@RequestMapping("/api")
class RiotController {
        @Value("\${app.riot}")
        val riot: String = ""



    @GetMapping("/riot.txt")
    fun download(): ResponseEntity<ByteArray> {
        val file = File("/Users/hamtory/Documents/github/BackEnd/RiotAPI/src/main/kotlin/com/api/study/riot_api/file/riot.txt")
        val path = Paths.get(file.absolutePath)

        val header = HttpHeaders();
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
}