package com.devtyagi.examportal.repository

import com.devtyagi.examportal.dao.Exam
import com.devtyagi.examportal.dao.User
import org.springframework.data.jpa.repository.JpaRepository

interface ExamRepository : JpaRepository<Exam, String> {

    fun findAllByCreatedBy_User(user: User): List<Exam>

}