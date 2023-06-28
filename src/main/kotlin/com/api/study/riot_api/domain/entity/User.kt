package com.api.study.riot_api.domain.entity

import javax.persistence.*


@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx: Long = 0L,
    var name: String = "",
    var id: String = "",
    var password: String = "",
    var lolId: Long? = null,
    var valId: Long? = null,

//    @OneToOne
//    @JoinColumn(name = "idx")
//    var lolUser: LolUser? = null,
//
//    @OneToOne
//    @JoinColumn(name = " idx")
//    var valUser: ValUser? = null

)