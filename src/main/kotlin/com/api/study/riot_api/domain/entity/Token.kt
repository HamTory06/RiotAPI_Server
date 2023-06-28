package com.api.study.riot_api.domain.entity

import javax.persistence.*

@Entity
@Table(name = "Token")
class Token (
    @Id
    var idx: Long,
    var accessToken: String? = null,
    var refreshToken: String? = null,
)