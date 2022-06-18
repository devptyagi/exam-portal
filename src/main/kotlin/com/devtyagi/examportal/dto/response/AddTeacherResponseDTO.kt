package com.devtyagi.examportal.dto.response

import com.devtyagi.examportal.dao.Subject
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
data class AddTeacherResponseDTO(
    val name: String,
    val email: String,
    val gender: String,
    val phoneNumber: String,
    val subjects: List<Subject>
)