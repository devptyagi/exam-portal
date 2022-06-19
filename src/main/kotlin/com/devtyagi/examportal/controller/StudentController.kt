package com.devtyagi.examportal.controller

import com.devtyagi.examportal.auth.CustomUserDetails
import com.devtyagi.examportal.constants.Endpoints
import com.devtyagi.examportal.dao.Exam
import com.devtyagi.examportal.dto.request.ExamSubmissionRequestDTO
import com.devtyagi.examportal.dto.request.LoginRequestDTO
import com.devtyagi.examportal.dto.response.ExamSubmissionResponseDTO
import com.devtyagi.examportal.dto.response.LoginStudentResponseDTO
import com.devtyagi.examportal.service.StudentService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping(Endpoints.BASE_URL)
class StudentController(
    private val studentService: StudentService
) {

    @GetMapping(Endpoints.StudentAPI.GET_EXAM_BY_ID)
    fun getExamByID(@PathVariable("examId") examId: String) : Exam {
        return studentService.getExamById(examId)
    }

    @PostMapping(Endpoints.StudentAPI.SUBMIT_EXAM)
    fun submitExam(@RequestBody examSubmissionRequestDTO: ExamSubmissionRequestDTO) : ExamSubmissionResponseDTO {
        val user = SecurityContextHolder.getContext().authentication.principal as CustomUserDetails
        val student = studentService.getStudentByUser(user.getUser())
        return studentService.submitExamResponse(examSubmissionRequestDTO, student)
    }

    @PostMapping(Endpoints.StudentAPI.LOGIN)
    fun loginStudent(@RequestBody loginRequestDTO: LoginRequestDTO) : LoginStudentResponseDTO {
        return studentService.loginStudent(loginRequestDTO)
    }

    @GetMapping(Endpoints.StudentAPI.GET_AVAILABLE_EXAMS)
    fun getAllAvailableExams(): List<Exam> {
        val user = SecurityContextHolder.getContext().authentication.principal as CustomUserDetails
        val student = studentService.getStudentByUser(user.getUser())
        return studentService.getAvailableExams(student)
    }

}