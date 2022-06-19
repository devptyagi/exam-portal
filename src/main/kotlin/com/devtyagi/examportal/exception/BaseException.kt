package com.devtyagi.examportal.exception

import org.springframework.http.HttpStatus

open class BaseException(
    message: String?,
    val responseHttpStatus: HttpStatus?
) : RuntimeException(message)