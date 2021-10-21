package com.dodo.dodolistserver.Project

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name="projects")
class Project (
    @Id @GeneratedValue
    val id: Long,
    val userId: Long,
    val isDaily: Boolean,
    val goal: String,
    val dueDate: LocalDateTime,
    val comment: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)