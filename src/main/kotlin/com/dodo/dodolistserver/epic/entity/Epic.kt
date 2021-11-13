package com.dodo.dodolistserver.epic.entity

import lombok.Getter
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Getter
@Table(name="epics")
class Epic (
    @Id
    @GeneratedValue
    val id: Long,
//    @ManyToOne
//    @JoinColumn(name="project_id")
//    val project: Project,
    val projectId: Long,
    val middleGoal: String,
    val dueDate: LocalDateTime,
    val createdAt: LocalDateTime?
)