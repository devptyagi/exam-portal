package com.devtyagi.examportal.exception

import org.springframework.http.HttpStatus

class TeacherNotFoundException: BaseException("No Teacher found with given ID", HttpStatus.BAD_GATEWAY)