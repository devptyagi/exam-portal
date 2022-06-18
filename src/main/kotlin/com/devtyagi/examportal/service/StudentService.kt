package com.devtyagi.examportal.service

import com.devtyagi.examportal.auth.CustomUserDetails
import com.devtyagi.examportal.auth.JwtUserDetailsService
import com.devtyagi.examportal.auth.JwtUtil
import com.devtyagi.examportal.dao.Exam
import com.devtyagi.examportal.dao.Student
import com.devtyagi.examportal.dao.User
import com.devtyagi.examportal.dto.request.AddStudentRequestDTO
import com.devtyagi.examportal.dto.request.LoginRequestDTO
import com.devtyagi.examportal.dto.response.AddStudentResponseDTO
import com.devtyagi.examportal.dto.response.LoginStudentResponseDTO
import com.devtyagi.examportal.dto.response.LoginTeacherResponseDTO
import com.devtyagi.examportal.enums.Gender
import com.devtyagi.examportal.enums.Role
import com.devtyagi.examportal.exception.InvalidCredentialsException
import com.devtyagi.examportal.exception.StudentNotFoundException
import com.devtyagi.examportal.repository.ExamRepository
import com.devtyagi.examportal.repository.StudentRepository
import com.devtyagi.examportal.repository.SubjectRepository
import com.devtyagi.examportal.util.EmailUtil
import com.devtyagi.examportal.util.RandomPasswordUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class StudentService(
    private val studentRepository: StudentRepository,
    private val emailUtil: EmailUtil,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: JwtUserDetailsService,
    private val subjectRepository: SubjectRepository,
    private val examRepository: ExamRepository,
    private val jwtUtil: JwtUtil
) {

    fun getAvailableExams(student: Student): List<Exam> {
        val allExams = mutableListOf<Exam>()
        for(subject in student.subjects) {
            val exams = examRepository.getAllAvailableExamsForStudent(Instant.now().toEpochMilli(), subject)
            allExams.addAll(exams)
        }
        return allExams
    }

    fun addStudent(addStudentRequestDTO: AddStudentRequestDTO): AddStudentResponseDTO {
        val gender = Gender.lookup(addStudentRequestDTO.gender)
        val password = RandomPasswordUtil.generateRandomPassword()
        val user = User(
            addStudentRequestDTO.name,
            addStudentRequestDTO.email,
            gender!!,
            Role.TEACHER,
            passwordEncoder.encode(password),
            addStudentRequestDTO.phoneNumber
        )
        val student = Student(
            user,
            addStudentRequestDTO.subjects
        )
        val savedStudent = studentRepository.save(student)
        emailUtil.sendWelcomeEmail(user, password)
        return savedStudent.toAddStudentResponseDTO()
    }

    fun getStudentById(user: User): Student {
        return studentRepository.findStudentByUser(user) ?: throw StudentNotFoundException()
    }

    fun loginStudent(loginRequestDTO: LoginRequestDTO): LoginStudentResponseDTO {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequestDTO.email,
                    loginRequestDTO.password
                )
            )
        } catch (exception: BadCredentialsException) {
            throw InvalidCredentialsException()
        }
        val userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.email) as CustomUserDetails
        val accessToken = jwtUtil.generateToken(userDetails)
        return LoginStudentResponseDTO(
            userDetails.getUser().name,
            userDetails.getUser().email,
            userDetails.getUser().gender.toString(),
            userDetails.getUser().phoneNumber,
            accessToken
        )
    }

}