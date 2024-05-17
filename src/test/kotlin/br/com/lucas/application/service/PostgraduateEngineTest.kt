package br.com.lucas.application.service

import br.com.lucas.domain.Course
import br.com.lucas.domain.Teacher
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID
import org.joda.time.DateTime

class PostgraduateEngineTest {

    @Test
    fun `returns zero value when there is no postgraduate courses`() {
        val courses = buildCourses(coursesCount = 5, postgraduate = false)
        val teacher = mockk<Teacher>(relaxed = true)
        every { teacher.courses } returns courses

        val score = PostgraduateEngine().calculate(teacher)

        assert(score.result.compareTo(BigDecimal.ZERO) == 0)
        assert(score.type == ScoreEngineType.POSTGRADUATE)
    }

    @Test
    fun `returns correct value for postgraduate courses`() {
        val courses = buildCourses(coursesCount = 1)
        val teacher = mockk<Teacher>(relaxed = true)
        every { teacher.courses } returns courses

        val score = PostgraduateEngine().calculate(teacher)

        assert(score.result.compareTo(BigDecimal.valueOf(160)) == 0)
        assert(score.type == ScoreEngineType.POSTGRADUATE)
    }

    private fun buildCourses(coursesCount: Int, postgraduate: Boolean = true): List<Course> =
        List(coursesCount) {
            Course(
                id = UUID.randomUUID(),
                hours = 80,
                date = DateTime.now().toDate(),
                postgraduate = postgraduate
            )
        }
}
