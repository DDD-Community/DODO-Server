package com.dodo.dodolistserver.epic.dto

import lombok.Builder
import lombok.Getter
import java.time.LocalDateTime

@Getter
@Builder
class CreateEpicRequestDto (
        var projectId: Long,
        val middleGoal: String,
        val dueDate: LocalDateTime,
        val createdAt: LocalDateTime?
        )
