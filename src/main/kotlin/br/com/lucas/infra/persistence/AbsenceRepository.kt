package br.com.lucas.infra.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AbsenceRepository : JpaRepository<AbsenceEntity, UUID>
