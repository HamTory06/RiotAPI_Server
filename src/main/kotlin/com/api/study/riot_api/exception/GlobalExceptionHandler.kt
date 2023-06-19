package com.api.study.riot_api.exception

import com.api.study.riot_api.controller.AccountController
import com.api.study.riot_api.domain.dto.ErrorDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springdoc.core.RequestBodyService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.MethodNotAllowedException
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class GlobalExceptionHandler {
    private val LOGGER: Logger = LoggerFactory.getLogger(AccountController::class.java)

    @ExceptionHandler(CustomException::class)
    fun handleIllegalArgumentException(ex: CustomException): ResponseEntity<ErrorDto> {
        return ResponseEntity(
            ErrorDto(ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage()),
            HttpStatus.valueOf(ex.getErrorCode().getStatus())
        )
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<ErrorDto>{
        return ResponseEntity(
            ErrorDto(
                400,
                ex.message!!
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleConstraintViolationException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorDto> {
        return ResponseEntity(
            ErrorDto(
                400,
                ex.allErrors[0].defaultMessage!!
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleEmptyResultDataAccessException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorDto>{
        return ResponseEntity(
            ErrorDto(
                400,
                ex.allErrors[0].defaultMessage!!
            ), HttpStatus.BAD_REQUEST
        )
    }
}