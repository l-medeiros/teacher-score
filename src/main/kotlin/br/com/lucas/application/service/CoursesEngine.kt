package br.com.lucas.application.service

import br.com.lucas.domain.Teacher
import java.math.BigDecimal
import java.util.Date
import org.joda.time.DateTime
import org.springframework.stereotype.Service

@Service
class CoursesEngine : ScoreEngine {

    companion object {
        private const val BONUS_PER_HOUR = 1
        private const val LIMIT_SCORE = 400
    }

    override val scoreEngineType: ScoreEngineType = ScoreEngineType.COURSES

    override fun calculate(teacher: Teacher): ScoreResult {
        val validCourses = teacher.courses.filter { it.isRegularCourse() && isDateCourseValid(it.date) }
        val validCourseScore = validCourses.sumOf { it.hours * BONUS_PER_HOUR }
        val effectiveScore = validCourseScore.coerceAtMost(LIMIT_SCORE)

        return ScoreResult(scoreEngineType, BigDecimal(effectiveScore))
    }

    private fun isDateCourseValid(date: Date): Boolean {
        val fourYearsAgo = DateTime.now().minusYears(4).toDate()
        return date.after(fourYearsAgo)
    }
}