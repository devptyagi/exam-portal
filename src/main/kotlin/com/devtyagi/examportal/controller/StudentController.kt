package com.devtyagi.examportal.controller

import com.devtyagi.examportal.auth.CustomUserDetails
import com.devtyagi.examportal.constants.Endpoints
import com.devtyagi.examportal.dao.Exam
import com.devtyagi.examportal.service.StudentService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping(Endpoints.BASE_URL)
class StudentController(
    private val studentService: StudentService
) {

    @GetMapping(Endpoints.StudentAPI.GET_AVAILABLE_EXAMS)
    fun getAllAvailableExams(): List<Exam> {
        val user = SecurityContextHolder.getContext().authentication.principal as CustomUserDetails
        val student = studentService.getStudentById(user.getUser())
        return studentService.getAvailableExams(student)
    }

}