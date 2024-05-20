package br.com.lucas.adapters.queue.listener

import br.com.lucas.application.service.ScoreService
import br.com.lucas.domain.AsyncScoreRequestedEvent
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class AsyncScoreRequestedListenerTest {

    @Test
    fun `call score service when consumes async score requested event`() {
        val event = AsyncScoreRequestedEvent()
        val scoreService = mockk<ScoreService>(relaxed = true)

        AsyncScoreRequestedListener(scoreService).onApplicationEvent(event)

        verify { scoreService.calculateAsync() }
    }
}
