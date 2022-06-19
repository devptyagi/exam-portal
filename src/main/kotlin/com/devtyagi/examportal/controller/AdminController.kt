package com.devtyagi.examportal.controller

import com.devtyagi.examportal.constants.Endpoints
import com.devtyagi.examportal.dao.Subject
import com.devtyagi.examportal.dto.request.AddStudentRequestDTO
import com.devtyagi.examportal.dto.request.AddTeacherRequestDTO
import com.devtyagi.examportal.dto.request.LoginRequestDTO
import com.devtyagi.examportal.dto.response.AddStudentResponseDTO
import com.devtyagi.examportal.dto.response.AddTeacherResponseDTO
import com.devtyagi.examportal.dto.response.LoginAdminResponseDTO
import com.devtyagi.examportal.repository.SubjectRepository
import com.devtyagi.examportal.service.AdminService
import com.devtyagi.examportal.service.StudentService
import com.devtyagi.examportal.service.SubjectService
import com.devtyagi.examportal.service.TeacherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Endpoints.BASE_URL)
class AdminController(
    private val teacherService: TeacherService,
    private val adminService: AdminService,
    private val subjectService: SubjectService,
    private val studentService: StudentService
) {

    @PostMapping(Endpoints.AdminAPI.ADD_STUDENT)
    fun addStudent(@RequestBody addStudentRequestDTO: AddStudentRequestDTO) : AddStudentResponseDTO {
        return studentService.addStudent(addStudentRequestDTO)
    }

    @PostMapping(Endpoints.AdminAPI.ADD_TEACHER)
    fun addTeacher(@RequestBody addTeacherRequestDTO: AddTeacherRequestDTO) : AddTeacherResponseDTO {
        return teacherService.addTeacher(addTeacherRequestDTO)
    }

    @PostMapping(Endpoints.AdminAPI.ADD_SUBJECT)
    fun addSubject(@RequestBody subject: Subject) : Subject {
        return subjectService.addSubject(subject)
    }

    @GetMapping(Endpoints.AdminAPI.GET_ALL_SUBJECTS)
    fun getAllSubjects() : List<Subject> {
        return subjectService.getAllSubjects()
    }

    @PostMapping(Endpoints.AdminAPI.LOGIN)
    fun loginAdmin(@RequestBody loginRequestDTO: LoginRequestDTO): LoginAdminResponseDTO {
        return adminService.loginAdmin(loginRequestDTO)
    }

}
