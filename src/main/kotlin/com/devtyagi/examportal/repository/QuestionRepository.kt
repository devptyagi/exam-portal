package com.devtyagi.examportal.repository

import com.devtyagi.examportal.dao.Question
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<Question, String> {
}