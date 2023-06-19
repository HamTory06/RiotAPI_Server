package com.api.study.riot_api.domain.entity

import javax.persistence.*

@Entity
@Table(name = "Token")
class Token (
    @Id
    var idx: Long = 0,

    @Column(unique = true)
    var accessToken: String? = null,

    @Column(unique = true)
    var refreshToken: String? = null
)