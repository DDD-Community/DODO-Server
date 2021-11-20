package com.dodo.dodolistserver.project.dto

import java.time.LocalDateTime

class EditProjectRequestDto (
    val id: Long,
    val isDaily: Boolean,
    val goal: String,
    val dueDate: LocalDateTime,
    val comment: String
)