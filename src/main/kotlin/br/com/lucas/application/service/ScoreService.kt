package br.com.lucas.application.service

import br.com.lucas.domain.Teacher
import org.springframework.stereotype.Service

@Service
class ScoreService(private val scoreEngines: List<ScoreEngine>) {

    fun calculate(teacher: Teacher): ScoreReport {
        val calculatedScores =  scoreEngines.map { it.calculate(teacher) }

        return ScoreReport(
            teacherId = teacher.id,
            scores = calculatedScores,
            result = calculatedScores.sumOf { it.result }
        )
    }
}