package com.dodo.dodolistserver.project.entity

import lombok.Getter
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Getter
@Table(name="projects")
class Project (
    @Id @GeneratedValue
    val id: Long,
    val userId: Long,
    var isDaily: Boolean,
    var goal: String,
    var dueDate: LocalDateTime,
    var comment: String,
    val createdAt: LocalDateTime?
)