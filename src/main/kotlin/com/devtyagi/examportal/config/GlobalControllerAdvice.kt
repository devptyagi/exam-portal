package com.devtyagi.examportal.config

import com.devtyagi.examportal.dto.response.BaseResponseDTO
import com.devtyagi.examportal.exception.BaseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(BaseException::class)
    fun exceptionHandler(exception: BaseException): ResponseEntity<BaseResponseDTO?>? {
        val response = BaseResponseDTO(exception.message!!)
        return ResponseEntity(response, exception.responseHttpStatus!!)
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    fun userNotFoundExceptionHandler(usernameNotFoundException: UsernameNotFoundException): ResponseEntity<BaseResponseDTO?>? {
        val response = BaseResponseDTO(usernameNotFoundException.message!!)
        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

}