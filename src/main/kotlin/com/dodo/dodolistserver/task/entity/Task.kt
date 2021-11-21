package com.dodo.dodolistserver.task.entity

import com.dodo.dodolistserver.epic.entity.Epic
import com.dodo.dodolistserver.user.entity.User
import lombok.Getter
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Getter
@Table(name="tasks")
class Task (
    @Id
    @GeneratedValue
    val id: Long,
    @ManyToOne
    @JoinColumn(name="epic_id")
    val epic: Epic,
    @ManyToOne
    @JoinColumn(name="user_id")
    val user: User,
    var isDone: Boolean,
    var toDo: String,
    var comment: String,
    var priority :Int,
    var dueDate: LocalDateTime,
    @CreationTimestamp
    val createdAt: LocalDateTime
)
