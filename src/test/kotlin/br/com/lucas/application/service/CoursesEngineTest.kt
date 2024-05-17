package br.com.lucas.application.service

import br.com.lucas.domain.Course
import br.com.lucas.domain.Teacher
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID
import org.joda.time.DateTime

class CoursesEngineTest {

    @Test
    fun `returns correct 0 if there is not courses`() {
        val courses = buildCourses(0)
        val teacher = mockk<Teacher>(relaxed = true)
        every { teacher.courses } returns courses

        val score = CoursesEngine().calculate(teacher)

        assert(score.result.compareTo(BigDecimal.ZERO) == 0)
        assert(score.type == ScoreEngineType.COURSES)
    }

    @Test
    fun `returns correct value for courses score`() {
        val courses = buildCourses(3)
        val teacher = mockk<Teacher>(relaxed = true)
        every { teacher.courses } returns courses

        val score = CoursesEngine().calculate(teacher)

        assert(score.result.compareTo(BigDecimal.valueOf(120)) == 0)
        assert(score.type == ScoreEngineType.COURSES)
    }

    @Test
    fun `returns correct value for score limit`() {
        val courses = buildCourses(500)
        val teacher = mockk<Teacher>(relaxed = true)
        every { teacher.courses } returns courses

        val score = CoursesEngine().calculate(teacher)

        assert(score.result.compareTo(BigDecimal.valueOf(400)) == 0)
        assert(score.type == ScoreEngineType.COURSES)
    }

    private fun buildCourses(coursesCount: Int): List<Course> =
        List(coursesCount) {
            Course(
                id = UUID.randomUUID(),
                hours = 40,
                date = DateTime.now().toDate()
            )
        }
}
