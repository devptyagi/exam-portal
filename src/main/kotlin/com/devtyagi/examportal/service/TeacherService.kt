package com.devtyagi.examportal.service

import com.devtyagi.examportal.auth.CustomUserDetails
import com.devtyagi.examportal.auth.JwtUserDetailsService
import com.devtyagi.examportal.auth.JwtUtil
import com.devtyagi.examportal.dao.Exam
import com.devtyagi.examportal.dao.Question
import com.devtyagi.examportal.dao.Teacher
import com.devtyagi.examportal.dao.User
import com.devtyagi.examportal.dto.request.AddExamRequestDTO
import com.devtyagi.examportal.dto.request.AddQuestionRequestDTO
import com.devtyagi.examportal.dto.request.AddTeacherRequestDTO
import com.devtyagi.examportal.dto.request.LoginRequestDTO
import com.devtyagi.examportal.dto.response.AddExamResponseDTO
import com.devtyagi.examportal.dto.response.AddQuestionResponseDTO
import com.devtyagi.examportal.dto.response.AddTeacherResponseDTO
import com.devtyagi.examportal.dto.response.LoginTeacherResponseDTO
import com.devtyagi.examportal.enums.Answer
import com.devtyagi.examportal.enums.Gender
import com.devtyagi.examportal.enums.Role
import com.devtyagi.examportal.exception.InvalidCredentialsException
import com.devtyagi.examportal.exception.QuestionDoesNotExistException
import com.devtyagi.examportal.exception.SubjectNotFoundException
import com.devtyagi.examportal.exception.TeacherNotFoundException
import com.devtyagi.examportal.repository.ExamRepository
import com.devtyagi.examportal.repository.QuestionRepository
import com.devtyagi.examportal.repository.SubjectRepository
import com.devtyagi.examportal.repository.TeacherRepository
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.Instant

@Service
class TeacherService(
    private val teacherRepository: TeacherRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: JwtUserDetailsService,
    private val questionRepository: QuestionRepository,
    private val subjectRepository: SubjectRepository,
    private val examRepository: ExamRepository,
    private val jwtUtil: JwtUtil,
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun createExam(addExamRequestDTO: AddExamRequestDTO): AddExamResponseDTO  {
        val questions = mutableListOf<Question>()
        var totalMarks = 0
        for(questionId in addExamRequestDTO.questions) {
            val question = questionRepository.findById(questionId).orElse(null) ?: throw QuestionDoesNotExistException()
            questions.add(question)
            totalMarks += question.marks
        }
        val teacher = teacherRepository.findById(addExamRequestDTO.createdBy).orElse(null) ?: throw TeacherNotFoundException()
        val subject = subjectRepository.findById(addExamRequestDTO.subject).orElse(null) ?: throw SubjectNotFoundException()
        val exam = Exam(
            addExamRequestDTO.title,
            subject,
            teacher,
            Instant.now().toEpochMilli(),
            addExamRequestDTO.startTime,
            addExamRequestDTO.endTime,
            totalMarks,
            questions
        )
        val savedExam = examRepository.save(exam)
        return AddExamResponseDTO(savedExam.examId!!)
    }

    fun addQuestion(addQuestionRequestDTO: AddQuestionRequestDTO) : AddQuestionResponseDTO {
        val question = Question(
            addQuestionRequestDTO.questionText,
            addQuestionRequestDTO.optionOne,
            addQuestionRequestDTO.optionTwo,
            addQuestionRequestDTO.optionThree,
            addQuestionRequestDTO.optionFour,
            Answer.lookup(addQuestionRequestDTO.answer)!!,
            addQuestionRequestDTO.marks
        )
        val savedQuestion = questionRepository.save(question)
        return AddQuestionResponseDTO(
            savedQuestion.questionText,
            savedQuestion.optionOne,
            savedQuestion.optionTwo,
            savedQuestion.optionThree,
            savedQuestion.optionFour,
            savedQuestion.answer.toString(),
            savedQuestion.questionId!!
        )
    }

    fun setImageForQuestion(questionId: String, multipartFile: MultipartFile) {
        val question = questionRepository.findById(questionId).orElse(null) ?: throw QuestionDoesNotExistException()
        question.image = multipartFile.bytes
        questionRepository.save(question)
    }

    fun getImageForQuestion(questionId: String) : ByteArray? {
        val question = questionRepository.findById(questionId).orElse(null) ?: throw QuestionDoesNotExistException()
        return question.image
    }

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
        val response = savedTeacher.toAddTeacherResponseDTO()
        return response
    }


    fun loginTeacher(loginRequestDTO: LoginRequestDTO): LoginTeacherResponseDTO {
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
        return LoginTeacherResponseDTO(
            userDetails.getUser().name,
            userDetails.getUser().email,
            userDetails.getUser().gender.toString(),
            userDetails.getUser().phoneNumber,
            accessToken
        )
    }

}