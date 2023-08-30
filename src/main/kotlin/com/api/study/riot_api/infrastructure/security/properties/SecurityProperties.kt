package com.api.study.riot_api.infrastructure.security.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
class SecurityProperties(
    secretKey: String,
    accessExp: Int,
    refreshExp: Int
) {
    val accessExp: Int = accessExp * 1000 * 60
    val refreshExp: Int = refreshExp * 1000 * 60
    val key = secretKey
}