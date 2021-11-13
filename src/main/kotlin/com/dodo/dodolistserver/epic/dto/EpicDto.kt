package com.dodo.dodolistserver.epic.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Getter
@Builder
class EpicDto (
    val middleGoal: String,
    val dueDate: LocalDateTime,
    val createdAt: LocalDateTime?
)
