package br.com.lucas.adapters.queue.publisher

interface EventPublisher {

    fun publish(content: Any)
}
