package com.api.study.riot_api.global.security.jwt

import com.api.study.riot_api.domain.auth.type.Authority
import com.api.study.riot_api.global.principle.CustomManagerDetailsService
import com.api.study.riot_api.global.principle.CustomUserDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class JwtParser(
    private val jwtProperties: JwtProperties,
    private val userDetailsService: CustomUserDetailsService,
    private val managerDetailsService: CustomManagerDetailsService
) {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun getAuthentication(token: String): Authentication{
        log.info(token)
        val claims = getClaims(token)

        if (claims.header[Header.JWT_TYPE] != JwtProvider.ACCESS) {
            throw TODO("예외 처리")
        }
        log.info(token)
        val userDetails = getDetails(claims.body)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(jwtProperties.key).build()
                .parseClaimsJws(token)
        } catch (e: Exception){
            when(e){
                else -> TODO("예외")
            }
        }
    }

    private fun getDetails(body: Claims): UserDetails {
        return when(body.get("authority").toString()){
            "USER" -> {
                log.info(body.get("authority").toString())
                userDetailsService.loadUserByUsername(body.id)
            }
            "MANAGER" -> {
                log.info(body.get("authority").toString())
                managerDetailsService.loadUserByUsername(body.id)
            }
            else -> {
                TODO("예외처리")
            }
        }
    }
}