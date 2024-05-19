package br.com.lucas.adapters.queue.listener

import br.com.lucas.application.service.ScoreService
import br.com.lucas.domain.AsyncScoreRequestedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class AsyncScoreRequestedListener(
    private val scoreService: ScoreService
) : ApplicationListener<AsyncScoreRequestedEvent> {

    override fun onApplicationEvent(event: AsyncScoreRequestedEvent) {
        scoreService.calculateAsync()
    }
}
