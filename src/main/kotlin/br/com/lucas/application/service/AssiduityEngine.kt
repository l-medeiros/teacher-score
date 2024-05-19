package br.com.lucas.application.service

import br.com.lucas.domain.Absence
import br.com.lucas.domain.Teacher
import java.math.BigDecimal
import org.springframework.stereotype.Service

@Service
class AssiduityEngine : ScoreEngine {

    private companion object {
        const val SCHOOL_DAYS = 200
        const val SCHOOL_MONTHS = 10
        const val MONTH_BONUS = 2
        const val DAY_BONUS = 0.1
    }

    override val scoreEngineType: ScoreEngineType = ScoreEngineType.ASSIDUITY

    override fun calculate(teacher: Teacher): Score {
        val absences = teacher.absences
        val monthsWithAbsence = discoverMonthsWithAbsence(absences)

        val workDays = SCHOOL_DAYS - absences.size
        val workMonths = SCHOOL_MONTHS - monthsWithAbsence

        val daysResult = calculateWorkDaysScore(workDays)
        val monthsResult = calculateWorkMonthsScore(workMonths)
        val result = daysResult + monthsResult

        return Score(scoreEngineType, result)
    }

    private fun calculateWorkDaysScore(workDays: Int) = BigDecimal.valueOf(DAY_BONUS) * workDays.toBigDecimal()

    private fun calculateWorkMonthsScore(workMonths: Int) = MONTH_BONUS.toBigDecimal() * workMonths.toBigDecimal()

    private fun discoverMonthsWithAbsence(absences: List<Absence>): Int = absences.map { it.date.month }.distinct().size
}
