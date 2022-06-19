package com.devtyagi.examportal.dao

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "exam_result")
@NoArgsConstructor
@AllArgsConstructor
@Data
class ExamResult(
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(
        name = "exam_id",
        referencedColumnName = "examId"
    )
    val exam: Exam,
    val marksObtained: Int,
    val percentage: Double,
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(
        name = "student_id",
        referencedColumnName = "studentId"
    )
    val student: Student,
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    val examResultId: String? = null
)