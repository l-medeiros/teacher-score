package br.com.lucas.adapters.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ScoreReportRepository : JpaRepository<ScoreReportEntity, UUID>
