package com.api.study.riot_api.domain.entity

import javax.persistence.*

@Entity
@Table(name = "lol_user")
class LolUser (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx: Long = 0,
    var lolUserLevel: Int? = null,
    var lolUserName: String? = null,
    var lolUserPuuId: String? = null,
    var lolUserId: String? = null,
    var lolUserAccountId: String? = null,

)