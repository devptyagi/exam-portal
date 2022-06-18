package com.devtyagi.examportal.exception

import org.springframework.http.HttpStatus

class StudentNotFoundException : BaseException("No Student found with given ID", HttpStatus.BAD_REQUEST)