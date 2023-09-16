package com.api.study.riot_api.global.error

import org.springframework.http.HttpStatus

data class ErrorCode (
    val status: HttpStatus,
    val message: String
){
    override fun toString(): String {
        return """
            {
                "status":"${this.status}",
                "message":"${this.message}"
            }
        """.trimIndent()
    }
}