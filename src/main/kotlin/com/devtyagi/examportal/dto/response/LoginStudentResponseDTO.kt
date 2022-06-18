package com.devtyagi.examportal.dto.response

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
    val phoneNumber: String,
    val accessToken: String
)