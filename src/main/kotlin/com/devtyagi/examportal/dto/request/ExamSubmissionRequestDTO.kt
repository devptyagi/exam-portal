package com.devtyagi.examportal.dto.request

import com.devtyagi.examportal.dao.QuestionResponse
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class ExamSubmissionRequestDTO(
    val examId: String,
    val responses: List<QuestionResponse>
)