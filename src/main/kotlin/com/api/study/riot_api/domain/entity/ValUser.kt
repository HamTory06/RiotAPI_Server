package com.api.study.riot_api.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "val_user")
class ValUser (
    @Id
    var idx: Long = 0,

    @Column(name = "val_user_level")
    var valUserLevel: Int? = null,

    @Column(name = "val_user_name")
    var valUserName: String? = null
)