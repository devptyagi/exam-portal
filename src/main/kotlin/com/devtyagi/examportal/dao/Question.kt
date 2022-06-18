package com.devtyagi.examportal.dao

import com.devtyagi.examportal.enums.Answer
import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@Data
class Question(
    val questionText: String,

    val optionOne: String,

    val optionTwo: String,

    val optionThree: String,

    val optionFour: String,

    val answer: Answer,

    val marks: Int,

    @Lob
    @JsonIgnore
    var image: ByteArray? = null,

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    val questionId: String? = null
)