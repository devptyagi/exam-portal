package com.devtyagi.examportal.dao

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "exam_submission")
@NoArgsConstructor
@AllArgsConstructor
@Data
class ExamSubmission(
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(
        name = "exam_id",
        referencedColumnName = "examId"
    )
    val exam: Exam,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_submission_id", referencedColumnName = "examSubmissionId")
    val responses: List<QuestionResponse>,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(
        name = "student_id",
        referencedColumnName = "studentId"
    )
    val student: Student,

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    val examSubmissionId: String? = null
)