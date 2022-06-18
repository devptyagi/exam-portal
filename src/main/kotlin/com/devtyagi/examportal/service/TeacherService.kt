package com.devtyagi.examportal.service

import com.devtyagi.examportal.dao.Teacher
import com.devtyagi.examportal.dao.User
import com.devtyagi.examportal.dto.request.AddTeacherRequestDTO
import com.devtyagi.examportal.dto.response.AddTeacherResponseDTO
import com.devtyagi.examportal.enums.Gender
import com.devtyagi.examportal.enums.Role
import com.devtyagi.examportal.repository.TeacherRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class TeacherService(
    private val teacherRepository: TeacherRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun addTeacher(addTeacherRequestDTO: AddTeacherRequestDTO) : AddTeacherResponseDTO {
        val gender = Gender.lookup(addTeacherRequestDTO.gender)
        val user = User(
            addTeacherRequestDTO.name,
            addTeacherRequestDTO.email,
            gender!!,
            Role.TEACHER,
            passwordEncoder.encode(addTeacherRequestDTO.password),
            addTeacherRequestDTO.phoneNumber
        )
        val teacher = Teacher(
            user,
            addTeacherRequestDTO.subjects
        )
        val savedTeacher = teacherRepository.save(teacher)
        return savedTeacher.toAddTeacherResponseDTO()
    }

}