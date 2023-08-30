package com.api.study.riot_api

import com.api.study.riot_api.infrastructure.security.properties.SecurityProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(SecurityProperties::class)
class RiotApiApplication

fun main(args: Array<String>) {
    runApplication<RiotApiApplication>(*args)
}
