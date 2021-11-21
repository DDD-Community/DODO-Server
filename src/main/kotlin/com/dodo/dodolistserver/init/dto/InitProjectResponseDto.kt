package com.dodo.dodolistserver.init.dto

import lombok.Builder
import lombok.Getter
import java.time.LocalDateTime

@Getter
@Builder
class InitProjectResponseDto(
    val id: Long,
    val userId: Long,
    var isDaily: Boolean,
    var goal: String,
    var dueDate: LocalDateTime,
    var comment: String,
    val createdAt: LocalDateTime?
)