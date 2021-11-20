package com.dodo.dodolistserver.epic.dto

import lombok.Builder
import lombok.Getter
import java.time.LocalDateTime

@Getter
@Builder
class CreateEpicRequestDto (
        val projectId: Long,
        val middleGoal: String,
        val dueDate: LocalDateTime,
        val createdAt: LocalDateTime?
        )
