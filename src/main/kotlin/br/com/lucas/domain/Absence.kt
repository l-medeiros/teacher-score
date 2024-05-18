package br.com.lucas.domain

import java.util.Date
import java.util.UUID

data class Absence(
    val id: UUID,
    val note: String? = null,
    val date: Date
)