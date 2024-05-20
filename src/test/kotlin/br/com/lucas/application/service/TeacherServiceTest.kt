package br.com.lucas.application.service

import br.com.lucas.adapters.persistence.TeacherEntity
import br.com.lucas.adapters.persistence.TeacherRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*
import org.junit.jupiter.api.Test

class TeacherServiceTest {

    @Test
    fun `call repository to find teacher by id`() {
        val teacherId = UUID.randomUUID()
        val teacherEntity = mockk<TeacherEntity>(relaxed = true)
        val teacherRepository = mockk<TeacherRepository>(relaxed = true)
        every { teacherRepository.findById(teacherId).get() } returns teacherEntity

        TeacherService(teacherRepository).find(teacherId)

        verify { teacherRepository.findById(teacherId) }
    }

    @Test
    fun `call repository to find all teachers`() {
        val teacherRepository = mockk<TeacherRepository>(relaxed = true)

        TeacherService(teacherRepository).findAll()

        verify { teacherRepository.findAll() }
    }
}
