package com.devtyagi.examportal.repository

import com.devtyagi.examportal.dao.Exam
import org.springframework.data.jpa.repository.JpaRepository

interface ExamRepository : JpaRepository<Exam, String> {

}