package com.devtyagi.examportal.dto.response

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class BaseResponseDTO(
    val message: String
)