package com.devtyagi.examportal.dao

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "subject")
@NoArgsConstructor
@AllArgsConstructor
@Data
class Subject(
    val name: String,
    val subjectCode: String,
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    val subjectId: String? = null
)