package com.api.study.riot_api.domain.auth

import org.springframework.data.redis.core.RedisHash
import java.util.*
import javax.persistence.Id


@RedisHash
data class RefreshTokenEntity(
    @Id
    val token: String,
    val userId: UUID,
)
