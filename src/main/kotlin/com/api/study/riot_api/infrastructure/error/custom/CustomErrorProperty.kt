package com.api.study.riot_api.infrastructure.error.custom

interface CustomErrorProperty {
    fun status(): Int
    fun message(): String
}