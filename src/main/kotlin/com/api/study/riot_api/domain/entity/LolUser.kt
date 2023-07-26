package com.api.study.riot_api.domain.entity

import java.time.LocalDateTime
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
    var lolUserId: String = "",
    var lolUserAccountId: String? = null,
    var lolUserUpdateTime: LocalDateTime
)