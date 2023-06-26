package com.api.study.riot_api.domain.entity

import javax.persistence.*


@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx: Long = 0L,
    @Column(name = "name", unique = true, nullable = false)
    var name: String = "",
    @Column(name = "id", unique = true, nullable = false)
    var id: String = "",
    @Column(name = "password", unique = false, nullable = false)
    var password: String = "",
    @Column(name = "mail", unique = true, nullable = false)
    var mail: String = "",
    @Column(name = "lol_id", unique = true)
    var lolId: Long? = null,
    @Column(name = "val_id", unique = true)
    var valId: Long? = null,

//    @OneToOne
//    @JoinColumn(name = "idx")
//    var lolUser: LolUser? = null,
//
//    @OneToOne
//    @JoinColumn(name = " idx")
//    var valUser: ValUser? = null

)