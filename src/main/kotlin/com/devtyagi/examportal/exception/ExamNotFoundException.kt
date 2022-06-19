package com.devtyagi.examportal.exception

import org.springframework.http.HttpStatus

class ExamNotFoundException: BaseException(
    "No exam exists with given ID!",
    HttpStatus.BAD_REQUEST
)