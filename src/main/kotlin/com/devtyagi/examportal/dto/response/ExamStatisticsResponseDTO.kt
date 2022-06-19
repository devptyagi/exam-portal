package com.devtyagi.examportal.dto.response

import com.devtyagi.examportal.dao.Exam
import com.devtyagi.examportal.dao.ExamResult
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class ExamStatisticsResponseDTO(
    val exam: Exam,
    val averageMarks: Double,
    val minimumMakrsObtained: Int,
    val maximumMarksObtained: Int,
    val examResults: List<ExamResult>
)