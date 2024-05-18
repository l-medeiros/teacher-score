package br.com.lucas.application.service

import br.com.lucas.domain.Teacher

interface ScoreEngine {

    val scoreEngineType: ScoreEngineType

    fun calculate(teacher: Teacher): ScoreResult
}
