package com.api.study.riot_api.domain.entity

import javax.persistence.*

@Entity
@Table(name = "lol_user")
class LolUser (
    @Id
    @Column(name = "idx")
    var idx: Long,

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