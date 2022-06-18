package com.devtyagi.examportal.dto.response

import com.devtyagi.examportal.enums.Role
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class LoginAdminResponseDTO(
    val role: Role,
    val accessToken: String
)