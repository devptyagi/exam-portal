package com.devtyagi.examportal.service

import com.devtyagi.examportal.dao.Subject
import com.devtyagi.examportal.repository.SubjectRepository
import org.springframework.stereotype.Service

@Service
class SubjectService(
    private val subjectRepository: SubjectRepository
) {

    fun addSubject(subject: Subject): Subject {
        return subjectRepository.save(subject)
    }

    fun getAllSubjects(): List<Subject> {
        return subjectRepository.findAll()
    }

}