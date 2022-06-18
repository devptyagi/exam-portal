package com.devtyagi.examportal.repository

import com.devtyagi.examportal.dao.Student
import com.devtyagi.examportal.dao.User
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, String> {

    fun findStudentByUser(user: User): Student?

}