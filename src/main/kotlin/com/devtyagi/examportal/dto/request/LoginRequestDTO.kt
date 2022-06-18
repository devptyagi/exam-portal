package com.devtyagi.examportal.dto.request

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class LoginRequestDTO(
    val email: String,
    val password: String,
)