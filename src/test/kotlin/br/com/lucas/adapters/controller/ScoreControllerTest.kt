package br.com.lucas.adapters.controller

import br.com.lucas.application.service.ScoreReportService
import br.com.lucas.application.service.TeacherService
import br.com.lucas.utils.buildTeacher
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import java.util.UUID
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@SpringBootTest
class ScoreControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean(relaxed = true)
    lateinit var scoreReportService: ScoreReportService

    @MockkBean
    private lateinit var teacherService: TeacherService

    @Test
    fun `returns 201 status when calculate for some teacher`() {
        val teacherId = UUID.randomUUID()
        val teacher = buildTeacher(teacherId)
        every { teacherService.find(teacherId) } returns teacher

        mockMvc
            .perform(post("/score/$teacherId"))
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun `returns 200 status when find all reports`() {
        mockMvc
            .perform(get("/score/reports"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
