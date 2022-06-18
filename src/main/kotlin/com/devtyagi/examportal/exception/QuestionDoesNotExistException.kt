package com.devtyagi.examportal.exception

import org.springframework.http.HttpStatus

class QuestionDoesNotExistException()
    : BaseException("No Question found with given ID", HttpStatus.BAD_REQUEST)