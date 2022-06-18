package com.devtyagi.examportal.repository

import com.devtyagi.examportal.dao.Exam
import com.devtyagi.examportal.dao.Subject
import com.devtyagi.examportal.dao.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ExamRepository : JpaRepository<Exam, String> {

    fun findAllByCreatedBy_User(user: User): List<Exam>

    @Query("select e from Exam e where e.endTime > :currentTime and e.subject = :subject")
    fun getAllAvailableExamsForStudent(currentTime: Long, subject: Subject): List<Exam>

}