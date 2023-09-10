package com.api.study.riot_api

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RiotApiApplicationTests {

    private val logger: Logger = LoggerFactory.getLogger(RiotApiApplicationTests::class.java)
    @Test
    fun contextLoads() {
        logger.info("Hello")
    }

}
