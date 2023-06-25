package com.api.study.riot_api.domain.entity

import javax.persistence.*

@Entity
@Table(name = "Token")
class Token (
    @Id
    @Column(name = "idx")
    var idx: Long,

    @Column(unique = true, name = "access_token")
    var accessToken: String? = null,

    @Column(unique = true, name = "refresh_token")
    var refreshToken: String? = null,
)