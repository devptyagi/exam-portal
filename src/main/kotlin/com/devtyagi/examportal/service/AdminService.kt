package com.devtyagi.examportal.service

import com.devtyagi.examportal.auth.JwtUserDetailsService
import com.devtyagi.examportal.auth.JwtUtil
import com.devtyagi.examportal.dao.User
import com.devtyagi.examportal.dto.request.LoginRequestDTO
import com.devtyagi.examportal.dto.response.LoginAdminResponseDTO
import com.devtyagi.examportal.enums.Gender
import com.devtyagi.examportal.enums.Role
import com.devtyagi.examportal.exception.InvalidCredentialsException
import com.devtyagi.examportal.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: JwtUserDetailsService,
    private val jwtUtil: JwtUtil,
    @Value("\${admin.email}")
    private val adminEmail: String,
    @Value("\${admin.password}")
    private val adminPassword: String
) {

    fun loginAdmin(loginRequestDTO: LoginRequestDTO): LoginAdminResponseDTO {
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
        val userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.email)
        val accessToken = jwtUtil.generateToken(userDetails)
        return LoginAdminResponseDTO(Role.ADMIN ,accessToken)
    }

    fun doesAdminExist() = userRepository.findUserByEmail(adminEmail) != null

    fun createAdmin() {
        val adminUser = User(
            name = "ADMIN",
            email = adminEmail,
            gender = Gender.MALE,
            role = Role.ADMIN,
            password = passwordEncoder.encode(adminPassword),
            phoneNumber = "9999999999"
        )
        userRepository.save(adminUser)
    }
}