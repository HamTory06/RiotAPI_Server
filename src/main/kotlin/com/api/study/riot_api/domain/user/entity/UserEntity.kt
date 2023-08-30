package com.api.study.riot_api.domain.user.entity

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "User")
class UserEntity (
    @Id
    val uuid: UUID,
    val id: String,
    val name: String,
    val password: String,
    val valId: String?,
    val lolId: String?
)