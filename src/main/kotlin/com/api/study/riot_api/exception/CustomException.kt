package com.api.study.riot_api.exception

import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
class CustomException(private val errorCode: ErrorCode) : RuntimeException() {

    fun getErrorCode(): ErrorCode {
        return errorCode
    }
}