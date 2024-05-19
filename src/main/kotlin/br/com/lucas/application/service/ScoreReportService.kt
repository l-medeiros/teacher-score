package br.com.lucas.application.service

import br.com.lucas.domain.Teacher
import br.com.lucas.infra.persistence.ScoreReportEntity
import br.com.lucas.infra.persistence.ScoreReportRepository
import org.springframework.stereotype.Component

@Component
class ScoreReportService(private val scoreReportRepository: ScoreReportRepository) {

    fun findAll() = scoreReportRepository.findAll().map { it.toDomain() }

    fun save(report: ScoreReport, teacher: Teacher) = ScoreReportEntity.fromDomain(report, teacher).let {
        scoreReportRepository.save(it)
    }
}
