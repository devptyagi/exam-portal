package com.devtyagi.examportal.dto.request

import com.devtyagi.examportal.dao.Question
import com.devtyagi.examportal.dao.Subject
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
data class AddExamRequestDTO(
    val title: String,
    val subject: String,
    val createdBy: String,
    val startTime: Long,
    val endTime: Long,
    val questions: List<String>
)