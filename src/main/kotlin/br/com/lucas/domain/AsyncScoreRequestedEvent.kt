package br.com.lucas.domain

import org.springframework.context.ApplicationEvent

data class AsyncScoreRequestedEvent(val source: String = "") : ApplicationEvent(source)
