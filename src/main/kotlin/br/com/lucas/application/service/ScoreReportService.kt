package br.com.lucas.application.service

import br.com.lucas.domain.Teacher
import br.com.lucas.adapters.persistence.ScoreReportEntity
import br.com.lucas.adapters.persistence.ScoreReportRepository
import br.com.lucas.domain.ScoreReport
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class ScoreReportService(private val scoreReportRepository: ScoreReportRepository) {

    fun find(teacherId: UUID?): List<ScoreReport> {
        val reports = teacherId?.let { listOf(findById(teacherId)) } ?: findAll()

        return reports.map { it.toDomain() }
    }

    fun save(report: ScoreReport, teacher: Teacher) = ScoreReportEntity.fromDomain(report, teacher).let {
        scoreReportRepository.save(it)
    }

    private fun findById(teacherId: UUID) = scoreReportRepository.findByTeacherId(teacherId)!!

    private fun findAll() = scoreReportRepository.findAll()
}
