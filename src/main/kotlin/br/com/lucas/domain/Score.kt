package br.com.lucas.domain

import br.com.lucas.application.service.ScoreEngineType
import java.math.BigDecimal

data class Score(val type: ScoreEngineType, val result: BigDecimal)
