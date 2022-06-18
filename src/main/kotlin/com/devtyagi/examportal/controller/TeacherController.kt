package com.devtyagi.examportal.controller

import com.devtyagi.examportal.constants.Endpoints
import com.devtyagi.examportal.dto.request.AddTeacherRequestDTO
import com.devtyagi.examportal.dto.response.AddTeacherResponseDTO
import com.devtyagi.examportal.service.TeacherService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Endpoints.BASE_URL)
class TeacherController(
    private val teacherService: TeacherService
) {

}