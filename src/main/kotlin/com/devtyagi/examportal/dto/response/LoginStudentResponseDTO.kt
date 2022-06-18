package com.devtyagi.examportal.dto.response

import com.devtyagi.examportal.enums.Role
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
data class LoginStudentResponseDTO(
    val name: String,
    val email: String,
    val gender: String,
    val role: Role,
    val phoneNumber: String,
    val accessToken: String
)