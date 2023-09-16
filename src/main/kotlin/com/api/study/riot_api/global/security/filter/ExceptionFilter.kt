package com.api.study.riot_api.global.security.filter

import com.api.study.riot_api.global.error.ErrorCode
import com.api.study.riot_api.global.security.exception.*
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.FilterChain


class ExceptionFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: InvalidTokenException) {
            e.printStackTrace()
            setErrorResponse(InvalidTokenException(), response)
        } catch (e: Exception) {
            e.printStackTrace()
            setErrorResponse(InternalServerErrorException(), response)
        }
    }

    private fun setErrorResponse(err: BusinessException, response: HttpServletResponse) {
        response.status = err.globalErrorCode.status().value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(ErrorCode(err.globalErrorCode.status(), err.globalErrorCode.message()).toString())
    }
}