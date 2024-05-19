package br.com.lucas.adapters.queue.publisher

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SpringDomainEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) : EventPublisher {

    override fun publish(content: Any) {
        applicationEventPublisher.publishEvent(content)
    }
}
