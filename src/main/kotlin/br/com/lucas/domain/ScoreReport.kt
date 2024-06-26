package br.com.lucas.domain

import java.math.BigDecimal
import java.util.UUID

data class ScoreReport(
    val teacherId: UUID,
    val scores: List<Score>,
    val result: BigDecimal
)
