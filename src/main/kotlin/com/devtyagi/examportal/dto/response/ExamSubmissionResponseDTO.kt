package com.devtyagi.examportal.dto.response

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class ExamSubmissionResponseDTO(
    val marksObtained: Int,
    val totalMarks: Int,
    val percentage: Double
)