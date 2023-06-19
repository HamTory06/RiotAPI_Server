package com.api.study.riot_api.domain.entity

import javax.persistence.*

@Entity
@Table(name = "lol_user")
class LolUser (
    @Id
    @Column(name = "idx")
    var idx: Long = 0L,

    @OneToOne
    @JoinColumn(name = "idx")
    var user: User? = null,

    @Column(name = "lol_user_level")
    var lolUserLevel: Int? = null,

    @Column(name = "lol_user_name")
    var lolUserName: String? = null
)