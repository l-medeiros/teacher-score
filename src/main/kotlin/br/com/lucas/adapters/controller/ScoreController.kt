package br.com.lucas.adapters.controller

import br.com.lucas.adapters.queue.publisher.EventPublisher
import br.com.lucas.application.service.ScoreReport
import br.com.lucas.application.service.ScoreReportService
import br.com.lucas.application.service.ScoreService
import br.com.lucas.domain.AsyncScoreRequestedEvent
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/score")
class ScoreController(
    private val scoreService: ScoreService,
    private val scoreReportService: ScoreReportService,
    private val publisher: EventPublisher
) {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    fun calculateAsync() = publisher.publish(AsyncScoreRequestedEvent())

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{teacherId}")
    fun calculateForTeacher(@PathVariable("teacherId") teacherId: UUID): ScoreReport =
        scoreService.calculate(teacherId)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/reports")
    fun findReports(): List<ScoreReport> = scoreReportService.findAll()
}
