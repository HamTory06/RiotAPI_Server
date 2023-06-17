package com.api.study.riot_api.exception

import com.api.study.riot_api.controller.AccountController
import com.api.study.riot_api.domain.dto.ErrorDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springdoc.core.RequestBodyService
import org.springframework.dao.DataIntegrityViolationException
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
                "중복된 값"
            ), HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleConstraintViolationException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorDto> {
        LOGGER.info("500")
        return ResponseEntity(
            ErrorDto(
                500,
                ex.allErrors[0].defaultMessage!!
            ), HttpStatus.INTERNAL_SERVER_ERROR
        )

        /**
         *         return ResponseEntity
         *                 .status(HttpStatus.BAD_REQUEST)
         *                 .body(new ExceptionResponse(e.getAllErrors().get(0).getDefaultMessage()));
         */

    }
}