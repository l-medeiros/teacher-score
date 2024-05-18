package br.com.lucas.application.service

import java.math.BigDecimal
import java.util.UUID

data class ScoreReport(
    val teacherId: UUID,
    val scores: List<ScoreResult>,
    val result: BigDecimal
)