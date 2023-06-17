package com.api.study.riot_api.exception

import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
enum class ErrorCode(private val status: Int, private val message: String) {
    NOT_HANGEUL_INTERNAL_SERVER_ERROR(500, "영어와 숫자만 입력 해주세요."),
    USER_NOT_FOUND_BAD_REQUEST(400,"유저를 못찾음"),
    PASSWORD_NOT_FOUND_BAS_REQUEST(400, "비밀번호가 일치하지 않습니다");

    fun getStatus(): Int {
        return status
    }

    fun getMessage(): String {
        return message
    }

}