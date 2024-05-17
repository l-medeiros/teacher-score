package br.com.lucas.application.service

import br.com.lucas.domain.Teacher
import java.time.Instant
import java.util.Date
import org.joda.time.LocalDate
import org.joda.time.Months
import org.joda.time.Years
import org.springframework.stereotype.Service


@Service
class ServiceTimeEngine : ScoreEngine {

    companion object {
        private const val BONUS_PER_SERVICE_MONTH = 1
        private const val BONUS_PER_SERVICE_YEAR = 5
    }

    override val scoreEngineType: ScoreEngineType = ScoreEngineType.SERVICE_TIME

    override fun calculate(teacher: Teacher): ScoreResult {
        val serviceMonths = discoverServiceMonths(teacher.hireDate)
        val serviceYears = discoverServiceYears(teacher.hireDate)

        val serviceMonthsScore = serviceMonths * BONUS_PER_SERVICE_MONTH
        val serviceYearsScore = serviceYears * BONUS_PER_SERVICE_YEAR
        val totalScore = serviceMonthsScore + serviceYearsScore

        return ScoreResult(scoreEngineType, totalScore.toBigDecimal())
    }

    private fun discoverServiceMonths(hireDate: Date): Int =
        Months
            .monthsBetween(LocalDate.fromDateFields(hireDate), LocalDate.fromDateFields(Date.from(Instant.now())))
            .months

    private fun discoverServiceYears(hireDate: Date): Int =
        Years
            .yearsBetween(LocalDate.fromDateFields(hireDate), LocalDate.fromDateFields(Date.from(Instant.now())))
            .years
}