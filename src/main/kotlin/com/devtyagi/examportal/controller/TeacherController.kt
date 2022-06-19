package com.devtyagi.examportal.controller

import com.devtyagi.examportal.auth.CustomUserDetails
import com.devtyagi.examportal.constants.Endpoints
import com.devtyagi.examportal.dao.Exam
import com.devtyagi.examportal.dto.request.AddExamRequestDTO
import com.devtyagi.examportal.dto.request.AddQuestionRequestDTO
import com.devtyagi.examportal.dto.request.AddStudentRequestDTO
import com.devtyagi.examportal.dto.request.LoginRequestDTO
import com.devtyagi.examportal.dto.response.*
import com.devtyagi.examportal.service.StudentService
import com.devtyagi.examportal.service.TeacherService
import io.jsonwebtoken.Jwts.header
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.*


@RestController
@RequestMapping(Endpoints.BASE_URL)
class TeacherController(
    private val teacherService: TeacherService,
    private val studentService: StudentService
) {

    @GetMapping(Endpoints.TeacherAPI.GET_STATS_FOR_EXAM)
    fun checkExamStatistics(@PathVariable("examId") examId: String): ExamStatisticsResponseDTO {
        return teacherService.getExamStatistics(examId)
    }

    @GetMapping(Endpoints.TeacherAPI.GET_ALL_EXAMS)
    fun getAllExamsByTeacher(): List<Exam> {
        val user = SecurityContextHolder.getContext().authentication.principal as CustomUserDetails
        return teacherService.getAllExamsByTeacher(user.getUser())
    }

    @PostMapping(Endpoints.TeacherAPI.LOGIN)
    fun loginTeacher(@RequestBody loginRequestDTO: LoginRequestDTO): LoginTeacherResponseDTO {
        return teacherService.loginTeacher(loginRequestDTO)
    }

    @PostMapping(Endpoints.TeacherAPI.CREATE_EXAM)
    fun createExam(@RequestBody addExamRequestDTO: AddExamRequestDTO): AddExamResponseDTO {
        val user = SecurityContextHolder.getContext().authentication.principal as CustomUserDetails
        val teacher = teacherService.getTeacherById(user.getUserId()!!)
        return teacherService.createExam(addExamRequestDTO, teacher)
    }

    @PostMapping(Endpoints.TeacherAPI.ADD_QUESTION)
    fun addQuestion(@RequestBody addQuestionRequestDTO: AddQuestionRequestDTO) : AddQuestionResponseDTO {
        return teacherService.addQuestion(addQuestionRequestDTO)
    }

    @PostMapping(Endpoints.TeacherAPI.UPLOAD_IMAGE)
    fun uploadImageForQuestion(
        @PathVariable("questionId") questionId: String,
        @RequestParam("image") multipartFile: MultipartFile
    ) : String{
        teacherService.setImageForQuestion(questionId, multipartFile)
        return "success"
    }

    @GetMapping(Endpoints.TeacherAPI.GET_IMAGE)
    fun getImageForQuestion(@PathVariable("questionId") questionId: String): ResponseEntity<Any> {
        val image = teacherService.getImageForQuestion(questionId)
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${System.currentTimeMillis()}\"")
            .body(image)
    }
}