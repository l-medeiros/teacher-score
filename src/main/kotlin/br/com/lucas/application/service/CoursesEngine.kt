package br.com.lucas.application.service

import br.com.lucas.domain.Course
import br.com.lucas.domain.Teacher
import java.math.BigDecimal
import java.util.Date
import org.joda.time.DateTime
import org.springframework.stereotype.Service

@Service
class CoursesEngine : ScoreEngine {

    private companion object {
        const val BONUS_PER_HOUR = 1
        const val LIMIT_SCORE = 400
    }

    override val scoreEngineType: ScoreEngineType = ScoreEngineType.COURSES

    override fun calculate(teacher: Teacher): Score {
        val validCourses = getValidCourses(teacher.courses)
        val validCourseScore = validCourses.sumOf { it.hours * BONUS_PER_HOUR }
        val effectiveScore = validCourseScore.coerceAtMost(LIMIT_SCORE)

        return Score(scoreEngineType, BigDecimal(effectiveScore))
    }

    private fun getValidCourses(courses: List<Course>) =
        courses.filter { it.isRegularCourse() && isDateCourseValid(it.date) }

    private fun isDateCourseValid(date: Date): Boolean {
        val fourYearsAgo = DateTime.now().minusYears(4).toDate()
        return date.after(fourYearsAgo)
    }
}
