package com.api.study.riot_api.exception

import lombok.AllArgsConstructor

@AllArgsConstructor
enum class ErrorCode(private val status: Int, private val message: String) {
    NOT_HANGEUL_INTERNAL_SERVER_ERROR(500, "영어와 숫자만 입력 해주세요."),
    USER_NOT_FOUND_BAD_REQUEST(400,"유저를 못찾음"),
    RIOT_API_ERROR(500, "라이엇 api 오류"),
    TOKEN_NOT_FOUND_FORBIDDEN_ERROR(403, "토큰 만료"),
    NOT_FOUND_USER_IDX_BAD_REQUEST(400, "요청하신 idx로 유저 정보를 찾을수 없습니다"),
    LOGIN_BAD_REQUEST(400,"비밀번호나 아이디가 틀렸습니다."),
    SERVER_ERROR(500, "무언가가 잘못됬습니다."),
    EMPTY_TEXT_BAD_REQUEST(400, "id가 비었습니다"),
    NOT_FOUND_IMAGE_BAD_REQUEST(400, "사진을 찾을수 없습니다"),
    PASSWORD_BAD_REQUEST(400, "대소문자, 특수문자, 숫자를 포함 시켜주세요.");


    fun getStatus(): Int {
        return status
    }

    fun getMessage(): String {
        return message
    }

}