package com.api.study.riot_api.exception

import feign.Response
import feign.codec.ErrorDecoder
import java.lang.Exception

class FeignCustomException: ErrorDecoder {

    override fun decode(methodKey: String?, response: Response?): Exception {
        return CustomException(ErrorCode.USER_NOT_FOUND_BAD_REQUEST)
    }
}