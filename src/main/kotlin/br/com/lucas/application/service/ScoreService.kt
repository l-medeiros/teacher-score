package br.com.lucas.application.service

import br.com.lucas.domain.Teacher
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
        val calculatedScores = calculateScores(teacher)
        val scoreReport = buildScoreReport(teacher, calculatedScores)

        return scoreReport.also {
            saveScoreReport(it, teacher)
        }
    }

    fun calculateAsync() {
        teacherService.findAll().forEach { teacher ->
            val calculatedScores = calculateScores(teacher)
            val scoreReport = buildScoreReport(teacher, calculatedScores)

            saveScoreReport(scoreReport, teacher)
        }
    }

    private fun calculateScores(teacher: Teacher) = scoreEngines.map { it.calculate(teacher) }

    private fun buildScoreReport(
        teacher: Teacher,
        calculatedScores: List<Score>
    ) = ScoreReport(
        teacherId = teacher.id,
        scores = calculatedScores,
        result = calculatedScores.sumOf { it.result }
    )

    private fun saveScoreReport(report: ScoreReport, teacher: Teacher) {
       scoreReportService.save(report, teacher)
    }
}
