package com.devtyagi.examportal.repository

import com.devtyagi.examportal.dao.ExamResult
import org.springframework.data.jpa.repository.JpaRepository

interface ExamResultRepository: JpaRepository<ExamResult, String> {
}