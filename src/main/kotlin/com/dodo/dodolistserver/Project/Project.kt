package com.dodo.dodolistserver.Project

import lombok.Getter
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*
import javax.persistence.*

@Entity
@Getter
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