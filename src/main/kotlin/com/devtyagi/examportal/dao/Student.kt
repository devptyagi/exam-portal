package com.devtyagi.examportal.dao

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Data
class Student(

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(
        name = "user_id",
        referencedColumnName = "userId"
    )
    private val user: User,

    @ManyToMany
    @JoinTable(
        name = "student_subject_map",
        joinColumns = [JoinColumn(
            name = "student_id",
            referencedColumnName = "studentId"
        )],
        inverseJoinColumns = [JoinColumn(
            name = "subject_id",
            referencedColumnName = "subjectId"
        )]
    )
    private val subjects: List<Subject>,

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private val studentId: String? = null
)