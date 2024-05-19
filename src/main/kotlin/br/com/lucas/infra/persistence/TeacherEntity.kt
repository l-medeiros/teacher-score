package br.com.lucas.infra.persistence

import br.com.lucas.domain.Teacher
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
    var absences: MutableList<AbsenceEntity> = mutableListOf(),
    @OneToMany(mappedBy = "teacher", cascade = [CascadeType.ALL], orphanRemoval = true)
    var reports: MutableList<ScoreReportEntity> = mutableListOf()
) {
    companion object {
        fun fromDomain(teacher: Teacher) = TeacherEntity(
            id = teacher.id,
            name = teacher.name,
            hireDate = teacher.hireDate
        )
    }

    fun toDomain() = Teacher(
        id = id,
        name = name,
        hireDate = hireDate,
        courses = courses.map { it.toDomain() },
        absences = absences.map { it.toDomain() }
    )
}
