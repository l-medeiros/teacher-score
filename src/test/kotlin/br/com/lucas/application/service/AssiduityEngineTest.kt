package br.com.lucas.application.service

import br.com.lucas.domain.Absence
import br.com.lucas.domain.Teacher
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import org.junit.jupiter.api.Test

class AssiduityEngineTest {

    @Test
    fun `returns correct value for assiduity`() {
        val teacher = mockk<Teacher>(relaxed = true)
        every { teacher.absences } returns buildAbsences()

        val score = AssiduityEngine().calculate(teacher)

        assert(score.result.compareTo(BigDecimal.valueOf(37)) == 0)
        assert(score.type == ScoreEngineType.ASSIDUITY)
    }

    private fun buildAbsences(): List<Absence> {
        val absences = mutableListOf<Absence>()
        val absence = mockk<Absence>()
        every { absence.date.month } returns 2
        repeat(10) {
            absences.add(absence)
        }
        return absences
    }
}