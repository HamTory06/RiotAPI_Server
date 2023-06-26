package com.api.study.riot_api.domain.entity

import javax.persistence.*

@Entity
@Table(name = "val_user")
class ValUser (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    val idx: Long = 0L,

    var valUserLevel: Int? = null,

    var valUserName: String = "",
)