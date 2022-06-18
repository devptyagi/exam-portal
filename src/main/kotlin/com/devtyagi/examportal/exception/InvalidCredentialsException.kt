package com.devtyagi.examportal.exception

import org.springframework.http.HttpStatus

class InvalidCredentialsException: BaseException(
    "Invalid Credentials!",
    HttpStatus.BAD_REQUEST
) {
}