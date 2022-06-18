package com.devtyagi.examportal.repository

import com.devtyagi.examportal.dao.Teacher
import org.springframework.data.jpa.repository.JpaRepository

interface TeacherRepository: JpaRepository<Teacher, String> {
}