package com.dodo.dodolistserver.project.dto

import lombok.Builder
import lombok.Getter
import java.time.LocalDateTime

@Getter
@Builder
class ProjectResponseDto(
    val isDaily: Boolean,
    val goal: String,
    val dueDate: LocalDateTime,
    val comment: String,
    val createdAt: LocalDateTime?
)