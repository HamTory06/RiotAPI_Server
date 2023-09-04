package com.api.study.riot_api.global.filter

import com.api.study.riot_api.infrastructure.security.exception.ExpiredTokenException
import com.api.study.riot_api.infrastructure.security.exception.InvalidTokenException
import com.api.study.riot_api.infrastructure.security.properties.SecurityProperties
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter(
    private val securityProperties: SecurityProperties
): OncePerRequestFilter() {

    companion object{
        const val PREFIX = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolvedToken(request)
        if(token.isEmpty())
            throw InvalidTokenException
        try {
            val claims = Jwts.parser()
                .setSigningKey(securityProperties.key)
                .parseClaimsJws(token)

            val jwtHeader = claims.header
            val jwtDody = claims.body

            val expiration = jwtDody.expiration
            filterChain.doFilter(request, response)

            if(expiration.before(Date())){
                throw ExpiredTokenException
            }
        } catch (e: Exception) {
            throw InvalidTokenException
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return true
    }



    private fun resolvedToken(request: HttpServletRequest): String{
        val bearerToken: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        bearerToken?.let {
            if(bearerToken.startsWith(PREFIX)){
                return bearerToken.substring(PREFIX.length)
            }
        }
        return ""
    }
}