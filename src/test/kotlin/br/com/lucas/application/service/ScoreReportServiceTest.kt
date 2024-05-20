package br.com.lucas.application.service

import br.com.lucas.adapters.persistence.ScoreReportEntity
import br.com.lucas.adapters.persistence.ScoreReportRepository
import io.mockk.every
import io.mockk.mockk
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ScoreReportServiceTest {

    @Test
    fun `return a single score report list when find is called with id`() {
        val teacherId = UUID.randomUUID()
        val report = mockk<ScoreReportEntity>(relaxed = true)
        val scoreReportRepository = mockk<ScoreReportRepository>(relaxed = true)
        every { scoreReportRepository.findById(teacherId).get() } returns report

        val reports = ScoreReportService(scoreReportRepository).find(teacherId)

        assertEquals(reports.size, 1)
    }

    @Test
    fun `return all reports when find is called with no id`() {
        val report = mockk<ScoreReportEntity>(relaxed = true)
        val scoreReportRepository = mockk<ScoreReportRepository>(relaxed = true)
        every { scoreReportRepository.findAll() } returns listOf(report, report)

        val reports = ScoreReportService(scoreReportRepository).find(null)

        assertEquals(reports.size, 2)
    }
}
