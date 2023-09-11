package com.api.study.riot_api.infrastructure.error.custom

abstract class CustomException(
    val errorProperty: CustomErrorProperty
): RuntimeException() {
    override fun fillInStackTrace() = this
}