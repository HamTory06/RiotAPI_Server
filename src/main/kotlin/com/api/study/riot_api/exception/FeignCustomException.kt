package com.api.study.riot_api.exception

import com.api.study.riot_api.service.RiotAPIService
import feign.Response
import feign.codec.ErrorDecoder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Exception

class FeignCustomException: ErrorDecoder {
    private val logger: Logger = LoggerFactory.getLogger(RiotAPIService::class.java)


    override fun decode(methodKey: String?, response: Response?): Exception {
        logger.info(response.toString())
        return CustomException(ErrorCode.RIOT_API_ERROR)
    }
}