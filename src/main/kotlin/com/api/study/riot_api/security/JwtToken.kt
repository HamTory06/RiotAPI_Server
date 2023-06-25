package com.api.study.riot_api.security

import com.api.study.riot_api.controller.AccountController
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration.*
import java.util.*

@Component
class JwtToken {

    @Value("\${app.secretKey}")
    private val secret: String = ""

    private val LOGGER: Logger = LoggerFactory.getLogger(AccountController::class.java)

    fun makeJwtAccessToken(userIdx: Long, email: String): String {
        val now = Date()

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + ofMinutes(30).toMillis()))
            .claim("idx", userIdx)
            .claim("email", email)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun makeJwtRefreshToken(): String {
        val now = Date()

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + ofHours(24).toMillis()))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        val token = token.replace("Bearer ", "") // "Bearer " 제거
        return try {
            val claims: Claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body

            val expiration = claims.expiration
            val now = Date()
            return expiration.before(now)
        } catch (e: Exception) {
            true
        }
    }
}