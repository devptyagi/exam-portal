package com.devtyagi.examportal.service

import com.devtyagi.examportal.dao.User
import com.devtyagi.examportal.enums.Gender
import com.devtyagi.examportal.enums.Role
import com.devtyagi.examportal.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    @Value("\${admin.email}")
    private val adminEmail: String,
    @Value("\${admin.password}")
    private val adminPassword: String
) {

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