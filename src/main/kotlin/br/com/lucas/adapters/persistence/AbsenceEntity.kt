package br.com.lucas.adapters.persistence

import br.com.lucas.domain.Absence
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID
import java.util.Date

@Entity
@Table(name = "absences")
data class AbsenceEntity(
    @Id
    @GeneratedValue
    val id: UUID,
    val note: String? = null,
    val date: Date,
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    var teacher: TeacherEntity? = null
) {
    fun toDomain() = Absence(
        id = id,
        note = note,
        date = date
    )
}
