package com.dodo.dodolistserver.task.dto

import java.time.LocalDateTime

class EditTaskRequestDto (
    val id: Long,
    val isDone: Boolean,
    val toDo: String,
    val comment: String,
    val priority :Int,
    val dueDate: LocalDateTime,
    val createdAt: LocalDateTime?
)