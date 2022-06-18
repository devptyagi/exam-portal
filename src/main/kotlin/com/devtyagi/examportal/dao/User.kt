package com.devtyagi.examportal.dao

import com.devtyagi.examportal.enums.Gender
import com.devtyagi.examportal.enums.Role
import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
class User(
    val name: String,

    @Column(nullable = false)
    val email: String,

    @Enumerated(EnumType.STRING)
    val gender: Gender,

    @Enumerated(EnumType.STRING)
    val role: Role,

    @Column(nullable = false)
    @JsonIgnore
    val password: String,

    val phoneNumber: String,

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    val userId: String? = null
)