package br.com.lucas.application.service

import br.com.lucas.domain.Teacher
import java.math.BigDecimal
import org.springframework.stereotype.Service

@Service
class PostgraduateEngine : ScoreEngine {

    private companion object {
        const val BONUS_PER_HOUR = 2
    }

    override val scoreEngineType: ScoreEngineType = ScoreEngineType.POSTGRADUATE

    override fun calculate(teacher: Teacher): Score {
        val validCourses = teacher.courses.filter { it.postgraduate }
        val validCoursesScore = validCourses.sumOf { it.hours * BONUS_PER_HOUR }

        return Score(scoreEngineType, BigDecimal(validCoursesScore))
    }
}
