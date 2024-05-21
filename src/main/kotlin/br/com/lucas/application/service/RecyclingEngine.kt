package br.com.lucas.application.service

import br.com.lucas.domain.Score
import br.com.lucas.domain.Teacher
import java.math.BigDecimal
import java.util.Date
import org.joda.time.DateTime
import org.springframework.stereotype.Service

@Service
class RecyclingEngine : ScoreEngine {

    private companion object {
        const val RECYCLING_COURSE_BONUS = 20
    }

    override val scoreEngineType: ScoreEngineType = ScoreEngineType.RECYCLING

    override fun calculate(teacher: Teacher): Score {
        val validCourses = teacher.courses.filter { it.recycling && isDateCourseValid(it.date) }
        val validCoursesScore = validCourses.size * RECYCLING_COURSE_BONUS

        return Score(scoreEngineType, BigDecimal(validCoursesScore))
    }

    private fun isDateCourseValid(date: Date): Boolean {
        val fourYearsAgo = DateTime.now().minusYears(2).toDate()
        return date.after(fourYearsAgo)
    }
}
