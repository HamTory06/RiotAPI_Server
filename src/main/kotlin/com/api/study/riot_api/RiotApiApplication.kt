package com.api.study.riot_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class RiotApiApplication

fun main(args: Array<String>) {
    runApplication<RiotApiApplication>(*args)
}
