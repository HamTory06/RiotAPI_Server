package com.api.study.riot_api.infrastructure.security.jwt

import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter: OncePerRequestFilter() {

    companion object{
        const val PREFIX = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolvedToken(request)

        if(token != null){

        }
    }

    private fun resolvedToken(request: HttpServletRequest): String?{
        val bearerToken: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        bearerToken?.let {
            if(bearerToken.startsWith(PREFIX)){
                return bearerToken.substring(PREFIX.length)
            }
        }
        return null
    }
}