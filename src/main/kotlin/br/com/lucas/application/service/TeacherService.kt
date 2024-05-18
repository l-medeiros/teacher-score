package br.com.lucas.application.service

import br.com.lucas.infra.persistence.TeacherRepository
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class TeacherService(private val teacherRepository: TeacherRepository) {

    fun find(teacherId: UUID) = teacherRepository.findById(teacherId).get().toDomain()
}
