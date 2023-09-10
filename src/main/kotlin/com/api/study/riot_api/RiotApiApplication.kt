package com.api.study.riot_api

import com.api.study.riot_api.global.security.jwt.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@ServletComponentScan
@EnableConfigurationProperties(JwtProperties::class)
class RiotApiApplication
fun main(args: Array<String>) {
    runApplication<RiotApiApplication>(*args)
}
