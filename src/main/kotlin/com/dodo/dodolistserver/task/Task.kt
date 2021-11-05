package com.dodo.dodolistserver.task

import lombok.Getter
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Getter
@Table(name="tasks")
class Task (
    @Id
    @GeneratedValue
    val id: Long,
//    @ManyToOne
//    @JoinColumn(name="epic_id")
//    val epic: Epic,
//    @ManyToOne
//    @JoinColumn(name="user_id")
//    val user: User,
    val epicId: Long,
    val userId: Long,
    val isDone: Boolean,
    val toDo: String,
    val comment: String,
    val priority :Int,
    val dueDate: LocalDateTime,
    val createdAt: LocalDateTime?
)
