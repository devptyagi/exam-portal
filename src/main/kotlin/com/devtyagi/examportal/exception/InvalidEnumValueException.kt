package com.devtyagi.examportal.exception

import org.springframework.http.HttpStatus

class InvalidEnumValueException(
    override val message: String
): BaseException(message, HttpStatus.BAD_REQUEST)