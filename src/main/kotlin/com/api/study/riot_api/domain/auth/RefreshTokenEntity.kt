package com.api.study.riot_api.domain.auth

import com.api.study.riot_api.domain.auth.type.Authority
import java.util.*
import javax.validation.constraints.NotNull
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "refresh_token")
data class RefreshTokenEntity(
    @Id
    val userId: UUID,
    @field:NotNull
    val token: String,
    @field:NotNull
    val authority: Authority,
    val expirationTime: Int
)
