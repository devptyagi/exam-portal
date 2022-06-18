package com.devtyagi.examportal.controller

import com.devtyagi.examportal.constants.Endpoints
import com.devtyagi.examportal.dto.request.AddExamRequestDTO
import com.devtyagi.examportal.dto.request.AddQuestionRequestDTO
import com.devtyagi.examportal.dto.request.LoginRequestDTO
import com.devtyagi.examportal.dto.response.AddExamResponseDTO
import com.devtyagi.examportal.dto.response.AddQuestionResponseDTO
import com.devtyagi.examportal.dto.response.LoginTeacherResponseDTO
import com.devtyagi.examportal.service.TeacherService
import io.jsonwebtoken.Jwts.header
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
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
    private val teacherService: TeacherService
) {

    @PostMapping(Endpoints.TeacherAPI.LOGIN)
    fun loginTeacher(@RequestBody loginRequestDTO: LoginRequestDTO): LoginTeacherResponseDTO {
        return teacherService.loginTeacher(loginRequestDTO)
    }
    
    @PostMapping(Endpoints.TeacherAPI.CREATE_EXAM)
    fun createExam(@RequestBody addExamRequestDTO: AddExamRequestDTO): AddExamResponseDTO {
        return teacherService.createExam(addExamRequestDTO)
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