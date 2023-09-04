package com.api.study.riot_api.infrastructure.security.jwt

import com.api.study.riot_api.domain.auth.RefreshTokenEntity
import com.api.study.riot_api.domain.auth.repositoy.RefreshTokenRepository
import com.api.study.riot_api.infrastructure.security.properties.SecurityProperties
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
@Component
class JwtProvider(
    private val securityProperties: SecurityProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    companion object{
        const val ACCESS = "access"
        const val REFRESH = "refresh"
    }
    fun generateJwtAccessToken(userId: UUID, id: String): String{
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, securityProperties.key)
            .setHeaderParam(Header.JWT_TYPE, ACCESS)
            .setId(userId.toString())
            .claim("id",id)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.accessExp))
            .compact()
    }

    fun generateJwtRefreshToken(userId: UUID): String{
        val token = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, securityProperties.key)
            .setHeaderParam(Header.JWT_TYPE, REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.refreshExp))
            .compact()

        val refreshToken = RefreshTokenEntity(
            token = token,
            userId = userId
        )

        refreshTokenRepository.save(refreshToken)

        return token
    }
}