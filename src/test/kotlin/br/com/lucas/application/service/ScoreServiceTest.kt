package br.com.lucas.application.service

import br.com.lucas.utils.buildTeacher
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import java.math.BigDecimal
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ScoreServiceTest {

    @Autowired
    private lateinit var scoreService: ScoreService

    @MockkBean(relaxed = true)
    lateinit var scoreReportService: ScoreReportService

    @MockkBean
    private lateinit var teacherService: TeacherService

    @Test
    fun `return correct ScoreReport and save it when calculate`() {
        val teacherId = UUID.randomUUID()
        val teacher = buildTeacher(teacherId)
        every { teacherService.find(teacherId) } returns teacher

        val scoreReport = scoreService.calculate(teacherId)

        assertEquals(scoreReport.teacherId, teacher.id)
        assertEquals(scoreReport.scores.size, 5)
        assertEquals(scoreReport.result, BigDecimal.valueOf(568.0))
        verify { scoreReportService.save(scoreReport, teacher) }
    }

    @Test
    fun `calculate for multiple teachers and save when calculate async correct ScoreReport`() {
        val teacherId = UUID.randomUUID()
        val teacher = buildTeacher(teacherId)
        every { teacherService.findAll() } returns listOf(teacher, teacher)

        scoreService.calculateAsync()

        verify(exactly = 2) { scoreReportService.save(any(), teacher) }
    }
}
