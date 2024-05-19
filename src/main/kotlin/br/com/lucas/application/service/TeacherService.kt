package br.com.lucas.application.service

import br.com.lucas.adapters.persistence.TeacherRepository
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class TeacherService(private val teacherRepository: TeacherRepository) {

    fun find(teacherId: UUID) = teacherRepository.findById(teacherId).get().toDomain()

    fun findAll() = teacherRepository.findAll().map { it.toDomain() }
}
