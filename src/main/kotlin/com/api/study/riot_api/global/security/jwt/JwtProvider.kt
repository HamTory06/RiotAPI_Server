package com.api.study.riot_api.global.security.jwt

import com.api.study.riot_api.domain.auth.RefreshTokenEntity
import com.api.study.riot_api.domain.auth.controller.dto.response.TokenResponseDto
import com.api.study.riot_api.domain.auth.repositoy.RefreshTokenRepository
import com.api.study.riot_api.domain.auth.type.Authority
import io.jsonwebtoken.*
import org.springframework.stereotype.Component
import java.util.*
import java.time.LocalDateTime

@Component
class JwtProvider(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    companion object{
        const val ACCESS = "access"
        const val REFRESH = "refresh"
        const val AUTHORITY = "authority"
    }

    fun receiveToken(userId: UUID, authority: Authority) = TokenResponseDto(
        accessToken = generateJwtAccessToken(userId, authority),
        expiredAt = LocalDateTime.now().plusSeconds(jwtProperties.accessExp.toLong()),
        refreshToken = generateJwtRefreshToken(userId, authority),
        authority = authority
    )
    private fun generateJwtAccessToken(userId: UUID, authority: Authority): String{
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.key)
            .setHeaderParam(Header.JWT_TYPE, ACCESS)
            .setId(userId.toString())
            .claim(AUTHORITY,authority.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp))
            .compact()
    }

    private fun generateJwtRefreshToken(userId: UUID, authority: Authority): String{
        val token = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, jwtProperties.key)
            .setHeaderParam(Header.JWT_TYPE, REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshExp))
            .compact()

        val refreshToken = RefreshTokenEntity(
            token = token,
            userId = userId,
            authority = authority,
            expirationTime = jwtProperties.refreshExp / 1000
        )

        refreshTokenRepository.save(refreshToken)

        return token
    }
}