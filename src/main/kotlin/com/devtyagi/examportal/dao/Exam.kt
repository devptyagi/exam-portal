package com.devtyagi.examportal.dao

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "exam")
@NoArgsConstructor
@AllArgsConstructor
@Data
class Exam(
    val title: String,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(
        name = "subject_id",
        referencedColumnName = "subjectId"
    )
    val subject: Subject,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(
        name = "teacher_id",
        referencedColumnName = "teacherId"
    )
    val createdBy: Teacher,

    val createdAt: Long,

    val startTime: Long,

    val endTime: Long,

    val maximumMarks: Int,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id", referencedColumnName = "examId")
    val questions: List<Question>,

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    val examId: String? = null
)