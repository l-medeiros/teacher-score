package br.com.lucas.adapters.queue.publisher

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationEventPublisher

class SpringDomainEventPublisherTest {

    @Test
    fun `call spring application event publisher to publish event`() {
        val content = "any-content"
        val applicationEventPublisher = mockk<ApplicationEventPublisher>(relaxed = true)

        SpringDomainEventPublisher(applicationEventPublisher).publish(content)

        verify { applicationEventPublisher.publishEvent(content) }
    }
}
