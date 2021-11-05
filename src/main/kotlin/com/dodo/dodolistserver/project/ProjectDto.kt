package com.dodo.dodolistserver.project

import lombok.Builder
import lombok.Getter
import java.time.LocalDateTime

@Getter
@Builder
class ProjectDto(
    val isDaily: Boolean,
    val goal: String,
    val dueDate: LocalDateTime,
    val comment: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)