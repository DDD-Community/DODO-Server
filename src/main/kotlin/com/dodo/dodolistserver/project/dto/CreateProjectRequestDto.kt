package com.dodo.dodolistserver.project.dto

import lombok.Builder
import lombok.Getter
import java.time.LocalDateTime

@Getter
@Builder
class CreateProjectRequestDto (
    val userId: Long,
    val isDaily: Boolean,
    val goal: String,
    val dueDate: LocalDateTime,
    val comment: String
)