package com.devtyagi.examportal.dto.response

import com.devtyagi.examportal.dao.Subject

data class AddTeacherResponseDTO(
    private val name: String,
    private val email: String,
    private val gender: String,
    private val phoneNumber: String,
    private val subjects: List<Subject>
)