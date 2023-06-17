package com.api.study.riot_api.security

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration.ofMinutes
import java.util.*

@Component
class JwtToken {

    @Value("\${app.secretKey}")
    private val secret: String = "";
    fun makeJwtToken(userIdx: Long, email: String): String {
        val now = Date()

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
            .setIssuer("fresh") // (2)
            .setIssuedAt(now) // (3)
            .setExpiration(Date(now.time + ofMinutes(30).toMillis())) // (4)
            .claim("id", userIdx) // (5)
            .claim("email", email)
            .signWith(SignatureAlgorithm.HS256, secret) // (6)
            .compact()
    }
}