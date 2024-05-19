package br.com.lucas.utils

import br.com.lucas.domain.Absence
import br.com.lucas.domain.Course
import br.com.lucas.domain.Teacher
import io.mockk.every
import io.mockk.mockk
import java.util.UUID
import org.joda.time.DateTime

fun buildTeacher(id: UUID) = Teacher(
    id = id,
    name = "test teacher",
    hireDate = DateTime.now().minusYears(3).toDate(),
    courses = buildCourses(),
    absences = buildAbsences()
)

private fun buildCourses() = listOf(
    buildRegularCourse(),
    buildPostgraduateCourse(),
    buildExtensionCourse()
)

private fun buildRegularCourse() = Course(
    id = UUID.randomUUID(),
    hours = 80,
    date = DateTime.now().minusYears(2).toDate()
)

private fun buildPostgraduateCourse() = Course(
    id = UUID.randomUUID(),
    hours = 200,
    date = DateTime.now().minusYears(2).toDate(),
    postgraduate = true
)

private fun buildExtensionCourse() = Course(
    id = UUID.randomUUID(),
    hours = 20,
    date = DateTime.now().minusYears(2).toDate(),
    recycling = true
)

private fun buildAbsences(): List<Absence> {
    val absences = mutableListOf<Absence>()
    val absence = mockk<Absence>()
    every { absence.date.month } returns 2
    repeat(10) { absences.add(absence) }

    return absences
}
