package com.api.study.riot_api.global.error.exception

import com.api.study.riot_api.global.error.ErrorCode
import com.api.study.riot_api.infrastructure.error.custom.CustomException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.net.BindException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(
        ex: MethodArgumentNotValidException
    ): ResponseEntity<ErrorCode> {

        return ResponseEntity(
            ErrorCode(
                HttpStatus.BAD_REQUEST,
                ex.allErrors[0].defaultMessage!!
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<ErrorCode>{
        return ResponseEntity(
            ErrorCode(
                HttpStatus.BAD_REQUEST,
                ex.message!!
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleEmptyResultDataAccessException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorCode>{
        return ResponseEntity(
            ErrorCode(
                HttpStatus.BAD_REQUEST,
                ex.allErrors[0].defaultMessage!!
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(ex: CustomException): ResponseEntity<ErrorCode>{
        return ResponseEntity(
            ErrorCode(
                ex.errorProperty.status(),
                ex.errorProperty.message()
            ),HttpStatus.BAD_REQUEST
        )
    }
}