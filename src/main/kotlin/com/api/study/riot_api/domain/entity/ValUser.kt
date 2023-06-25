package com.api.study.riot_api.domain.entity

import javax.persistence.*

@Entity
@Table(name = "val_user")
class ValUser (
    @Id
    @Column(name = "idx")
    var idx: Long,

    @Column(name = "val_user_level")
    var valUserLevel: Int? = null,

    @Column(name = "val_user_name")
    var valUserName: String? = null,
)