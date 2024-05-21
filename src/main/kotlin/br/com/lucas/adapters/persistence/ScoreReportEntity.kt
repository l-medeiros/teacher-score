package br.com.lucas.adapters.persistence

import br.com.lucas.domain.ScoreReport
import br.com.lucas.domain.Teacher
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "reports")
data class ScoreReportEntity(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    var teacher: TeacherEntity? = null,
    @Column(nullable = false)
    val result: BigDecimal
) {
    companion object {
        fun fromDomain(report: ScoreReport, teacher: Teacher) = ScoreReportEntity(
            teacher = TeacherEntity.fromDomain(teacher),
            result = report.result
        )
    }

    fun toDomain() = ScoreReport(
        teacherId = teacher!!.id,
        result = result,
        scores = emptyList()
    )
}
