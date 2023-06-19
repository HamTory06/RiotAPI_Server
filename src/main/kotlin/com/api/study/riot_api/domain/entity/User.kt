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

    @OneToOne
    @JoinColumn(name = "val_user_name", unique = true)
    var valUserName: ValUser? = null,

    @OneToOne
    @JoinColumn(name = "lol_user_name", unique = true)
    var lolUserName: LolUser? = null,
)