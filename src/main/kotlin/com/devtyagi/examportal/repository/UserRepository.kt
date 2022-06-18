package com.devtyagi.examportal.repository

import com.devtyagi.examportal.dao.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {

    fun findUserByEmail(email: String): User?

}