package br.com.lucas.application.service

import br.com.lucas.domain.Teacher
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import org.joda.time.DateTime
import org.junit.jupiter.api.Test

class ServiceTimeEngineTest {

    @Test
    fun `returns correct value for service time`() {
        val hireDate = DateTime.now().minusYears(3).toDate()
        val teacher = mockk<Teacher>(relaxed = true)
        every { teacher.hireDate } returns hireDate

        val score = ServiceTimeEngine().calculate(teacher)

        assert(score.result.compareTo(BigDecimal.valueOf(51)) == 0)
        assert(score.type == ScoreEngineType.SERVICE_TIME)
    }
}