package br.com.lucas.application.service

import br.com.lucas.domain.Course
import br.com.lucas.domain.Teacher
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID
import org.joda.time.DateTime

class RecyclingEngineTest {

    @Test
    fun `returns zero value when there is no valid recycling courses`() {
        val courses = buildCourses(5, recycling = false)
        val teacher = mockk<Teacher>(relaxed = true)
        every { teacher.courses } returns courses

        val score = RecyclingEngine().calculate(teacher)

        assert(score.result.compareTo(BigDecimal.ZERO) == 0)
        assert(score.type == ScoreEngineType.RECYCLING)
    }

    @Test
    fun `returns correct value for valid recycling courses`() {
        val courses = buildCourses(3, recycling = true)
        val teacher = mockk<Teacher>(relaxed = true)
        every { teacher.courses } returns courses

        val score = RecyclingEngine().calculate(teacher)

        assert(score.result.compareTo(BigDecimal.valueOf(60)) == 0)
        assert(score.type == ScoreEngineType.RECYCLING)
    }

    private fun buildCourses(coursesCount: Int, recycling: Boolean): List<Course> =
        List(coursesCount) {
            Course(
                id = UUID.randomUUID(),
                hours = 20,
                date = DateTime.now().toDate(),
                recycling = recycling
            )
        }
}
