package com.dodo.dodolistserver.task.entity

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
    var isDone: Boolean,
    var toDo: String,
    var comment: String,
    var priority :Int,
    var dueDate: LocalDateTime,
    val createdAt: LocalDateTime?
)
