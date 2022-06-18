package com.devtyagi.examportal.dao

import com.devtyagi.examportal.dto.response.AddStudentResponseDTO
import com.devtyagi.examportal.dto.response.AddTeacherResponseDTO
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
    val user: User,

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
    val subjects: List<Subject>,

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    val studentId: String? = null
){
    fun toAddStudentResponseDTO() = AddStudentResponseDTO(
        name = user.name,
        email = user.email,
        gender = user.gender.toString(),
        phoneNumber = user.phoneNumber,
        subjects = subjects
    )
}