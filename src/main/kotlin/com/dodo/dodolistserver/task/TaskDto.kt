package com.dodo.dodolistserver.task

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import java.time.LocalDateTime

@Getter
@Builder
class TaskDto(
    val isDone: Boolean,
    val toDo: String,
    val comment: String,
    val priority :Int,
    val dueDate: LocalDateTime,
    val createdAt: LocalDateTime?
)