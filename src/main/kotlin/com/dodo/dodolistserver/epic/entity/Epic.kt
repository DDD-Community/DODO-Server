package com.dodo.dodolistserver.epic.entity

import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Getter
@Setter
@Table(name="epics")
class Epic (
    @Id
    @GeneratedValue
    val id: Long,
//    @ManyToOne
//    @JoinColumn(name="project_id")
//    val project: Project,
    val projectId: Long,
    var middleGoal: String,
    var dueDate: LocalDateTime,
    var createdAt: LocalDateTime?
)