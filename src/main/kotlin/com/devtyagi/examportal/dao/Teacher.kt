package com.devtyagi.examportal.dao

import com.devtyagi.examportal.dto.response.AddTeacherResponseDTO
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "teacher")
@NoArgsConstructor
@AllArgsConstructor
@Data
class Teacher(

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(
        name = "user_id",
        referencedColumnName = "userId"
    )
    private val user: User,

    @ManyToMany
    @JoinTable(
        name = "teacher_subject_map",
        joinColumns = [JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
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
    private val teacherId: String? = null
) {
    fun toAddTeacherResponseDTO() = AddTeacherResponseDTO(
        name = user.name,
        email = user.email,
        gender = user.gender.toString(),
        phoneNumber = user.phoneNumber,
        subjects = subjects
    )
}