package br.com.lucas.application.service

import br.com.lucas.domain.Absence
import br.com.lucas.domain.Teacher
import java.math.BigDecimal
import org.springframework.stereotype.Service

@Service
class AssiduityEngine : ScoreEngine {

    companion object {
        private const val SCHOOL_DAYS = 200
        private const val SCHOOL_MONTHS = 10
        private const val MONTH_BONUS = 2
        private const val DAY_BONUS = 0.1
    }

    override val scoreEngineType: ScoreEngineType = ScoreEngineType.ASSIDUITY

    override fun calculate(teacher: Teacher): ScoreResult {
        val absences = teacher.absences
        val monthsWithAbsence = discoverMonthsWithAbsence(absences)

        val workDays = SCHOOL_DAYS - absences.size
        val workMonths = SCHOOL_MONTHS - monthsWithAbsence

        val daysResult = BigDecimal.valueOf(DAY_BONUS) * workDays.toBigDecimal()
        val monthsResult = MONTH_BONUS.toBigDecimal() * workMonths.toBigDecimal()
        val result = daysResult + monthsResult

        return ScoreResult(scoreEngineType, result)
    }

    private fun discoverMonthsWithAbsence(absences: List<Absence>): Int = absences.map { it.date.month }.distinct().size
}