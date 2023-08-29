package com.api.study.riot_api.domain.auth

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
class UserEntity (
    @Id
    val uuid: UUID,
    val id: String,
    val name: String,
    val password: String
)