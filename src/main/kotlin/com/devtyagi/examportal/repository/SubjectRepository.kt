package com.devtyagi.examportal.repository

import com.devtyagi.examportal.dao.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository: JpaRepository<Subject, String> {
}