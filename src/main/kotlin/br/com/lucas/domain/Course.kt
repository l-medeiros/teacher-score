package br.com.lucas.domain

import java.sql.Date
import java.util.UUID

data class Course(
    var id: UUID,
    val description: String? = null,
    val postgraduate: Boolean = false,
    val recycling: Boolean = false,
    val hours: Int,
    val date: Date
) {
    fun isRegularCourse() = !postgraduate && !recycling
}
