package br.com.lucas.application.service

import java.util.UUID
import org.springframework.stereotype.Service

@Service
class ScoreService(
    private val scoreEngines: List<ScoreEngine>,
    private val teacherService: TeacherService,
    private val scoreReportService: ScoreReportService
) {

    fun calculate(teacherId: UUID): ScoreReport {
        val teacher = teacherService.find(teacherId)
        val calculatedScores = scoreEngines.map { it.calculate(teacher) }

        return ScoreReport(
            teacherId = teacherId,
            scores = calculatedScores,
            result = calculatedScores.sumOf { it.result }
        ).also { scoreReport ->
            scoreReportService.save(scoreReport, teacher)
        }
    }
}
