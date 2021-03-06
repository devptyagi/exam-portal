package com.devtyagi.examportal.dao

import com.devtyagi.examportal.enums.Answer
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "question_response")
@NoArgsConstructor
@AllArgsConstructor
@Data
class QuestionResponse(
    val questionId: String,
    val selectedOption: Answer,
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    val questionResponseId: String? = null
)