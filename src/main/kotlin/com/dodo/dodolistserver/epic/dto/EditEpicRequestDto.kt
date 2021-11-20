package com.dodo.dodolistserver.epic.dto

import lombok.Getter
import lombok.Setter
import java.time.LocalDateTime

@Getter
class EditEpicRequestDto(
        val id: Long,
        val middleGoal: String,
        val dueDate: LocalDateTime,
        val createdAt: LocalDateTime?
)