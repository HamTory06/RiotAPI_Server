package com.api.study.riot_api.exception

import lombok.AllArgsConstructor

@AllArgsConstructor
enum class ErrorCode(private val status: Int, private val message: String) {
    NOT_HANGEUL_INTERNAL_SERVER_ERROR(500, "영어와 숫자만 입력 해주세요."),
    USER_NOT_FOUND_BAD_REQUEST(400,"유저를 못찾음"),
    PASSWORD_NOT_FOUND_BAD_REQUEST(400, "비밀번호가 일치하지 않습니다"),
    TOKEN_NOT_FOUND_FORBIDDEN_ERROR(403, "토큰 만료"),
    NOT_FOUND_USER_IDX_BAD_REQUEST(400, "요청하신 idx로 유저 정보를 찾을수 없습니다"),
    NOT_FOUND_USER_ID_BAD_REQUEST(400, "요청하신 id가 존재 하지 않습니다"),
    NOT_FOUND_USER_PASSWORD_BAD_REQUEST(400, "Password가 클렸습니다");


    fun getStatus(): Int {
        return status
    }

    fun getMessage(): String {
        return message
    }

}