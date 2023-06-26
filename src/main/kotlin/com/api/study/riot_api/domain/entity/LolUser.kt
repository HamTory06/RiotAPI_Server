package com.api.study.riot_api.domain.entity

import javax.persistence.*

@Entity
@Table(name = "lol_user")
class LolUser (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    val idx: Long = 0,

    @Column(name = "lol_user_level")
    var lolUserLevel: Int? = null,

    @Column(name = "lol_user_name")
    var lolUserName: String? = null,

    @Column(name = "lol_user_puuid")
    var lolUserPuuId: String? = null,

    @Column(name = "lol_user_id")
    var lolUserId: String? = null,

    @Column(name = "lol_user_accountId")
    var lolUserAccountId: String? = null,

)