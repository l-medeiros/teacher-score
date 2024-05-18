package br.com.lucas.application.service

import br.com.lucas.domain.Absence
import br.com.lucas.domain.Course
import br.com.lucas.domain.Teacher
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.util.UUID
import org.joda.time.DateTime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ScoreServiceTest {

    @Autowired
    private lateinit var scoreService: ScoreService

    @MockkBean
    private lateinit var teacherService: TeacherService

    @Test
    fun `calculate should return correct ScoreReport`() {
        val teacherId = UUID.randomUUID()
        val teacher = buildTeacher(teacherId)
        every { teacherService.find(teacherId) } returns teacher

        val scoreReport = scoreService.calculate(teacherId)

        assertEquals(scoreReport.teacherId, teacher.id)
        assertEquals(scoreReport.scores.size, 5)
        assertEquals(scoreReport.result, BigDecimal.valueOf(568.0))
    }

    private fun buildTeacher(id: UUID) = Teacher(
        id = id,
        name = "test teacher",
        hireDate = DateTime.now().minusYears(3).toDate(),
        courses = buildCourses(),
        absences = buildAbsences()
    )

    private fun buildCourses() = listOf(
        buildRegularCourse(),
        buildPostgraduateCourse(),
        buildExtensionCourse()
    )

    private fun buildRegularCourse() = Course(
        id = UUID.randomUUID(),
        hours = 80,
        date = DateTime.now().minusYears(2).toDate()
    )

    private fun buildPostgraduateCourse() = Course(
        id = UUID.randomUUID(),
        hours = 200,
        date = DateTime.now().minusYears(2).toDate(),
        postgraduate = true
    )

    private fun buildExtensionCourse() = Course(
        id = UUID.randomUUID(),
        hours = 20,
        date = DateTime.now().minusYears(2).toDate(),
        recycling = true
    )

    private fun buildAbsences(): List<Absence> {
        val absences = mutableListOf<Absence>()
        val absence = mockk<Absence>()
        every { absence.date.month } returns 2
        repeat(10) { absences.add(absence) }

        return absences
    }
}
