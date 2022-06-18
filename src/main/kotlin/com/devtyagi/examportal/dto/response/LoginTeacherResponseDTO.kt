package com.devtyagi.examportal.dto.response

import com.devtyagi.examportal.dao.Subject
import com.devtyagi.examportal.enums.Role
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
data class LoginTeacherResponseDTO(
    val name: String,
    val email: String,
    val gender: String,
    val role: Role,
    val phoneNumber: String,
    val accessToken: String
)