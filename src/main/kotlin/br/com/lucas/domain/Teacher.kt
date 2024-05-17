package br.com.lucas.domain

import java.util.UUID
import java.util.Date

data class Teacher(
    var id: UUID,
    val name: String,
    val hireDate: Date,
    val courses: List<Course> = emptyList(),
    val absences: List<Absence> = emptyList()
)
