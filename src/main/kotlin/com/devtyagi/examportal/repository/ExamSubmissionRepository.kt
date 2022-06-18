package com.devtyagi.examportal.repository

import com.devtyagi.examportal.dao.ExamSubmission
import org.springframework.data.jpa.repository.JpaRepository

interface ExamSubmissionRepository: JpaRepository<ExamSubmission, String> {
}