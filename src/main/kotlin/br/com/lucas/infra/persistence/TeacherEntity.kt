package br.com.lucas.infra.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.CascadeType
import jakarta.persistence.OneToMany
import java.util.UUID
import java.util.Date

@Entity
@Table(name = "teachers")
data class TeacherEntity(
    @Id
    @GeneratedValue
    var id: UUID,
    val name: String,
    val hireDate: Date,
    @OneToMany(mappedBy = "teacher", cascade = [CascadeType.ALL], orphanRemoval = true)
    var courses: MutableList<CourseEntity> = mutableListOf(),
    @OneToMany(mappedBy = "teacher", cascade = [CascadeType.ALL], orphanRemoval = true)
    var absences: MutableList<AbsenceEntity> = mutableListOf()
)
