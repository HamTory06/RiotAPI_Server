package com.api.study.riot_api.domain.dto

import javax.persistence.Column

data class UpdateUserDto(
    @field:Column(unique = true)
    val lolName: String?,
//    @field:Column(unique = true)
//    val valName: String?,
)