package com.devtyagi.examportal.exception

import org.springframework.http.HttpStatus

open class BaseException(
    message: String?,
    responseHttpStatus: HttpStatus?
) : RuntimeException(message)