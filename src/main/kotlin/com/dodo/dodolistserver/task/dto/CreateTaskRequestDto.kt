package com.dodo.dodolistserver.task.dto

import java.time.LocalDateTime

class CreateTaskRequestDto(
        val epicId: Long,
        val userId: Long,
        val isDone: Boolean,
        val toDo: String,
        val comment: String,
        val priority :Int,
        val dueDate: LocalDateTime,
        val createdAt: LocalDateTime?
)