package br.com.lucas.infra.persistence

import br.com.lucas.domain.Course
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID
import java.util.Date

@Entity
@Table(name = "courses")
data class CourseEntity(
    @Id
    @GeneratedValue
    var id: UUID,
    val description: String? = null,
    val postgraduate: Boolean = false,
    val recycling: Boolean = false,
    val hours: Int,
    val date: Date,
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    var teacher: TeacherEntity? = null
) {
    fun toDomain() = Course(
        id = id,
        description = description,
        postgraduate = postgraduate,
        recycling = recycling,
        hours = hours,
        date = date
    )
}
