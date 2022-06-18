package com.devtyagi.examportal.exception

import org.springframework.http.HttpStatus

class SubjectNotFoundException: BaseException("No Subject found with given ID", HttpStatus.BAD_GATEWAY)