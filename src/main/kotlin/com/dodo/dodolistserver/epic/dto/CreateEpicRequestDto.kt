package com.dodo.dodolistserver.epic.dto

import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiParam
import lombok.Builder
import lombok.Getter
import java.time.LocalDateTime

@Getter
@Builder
class CreateEpicRequestDto (
        @ApiParam(value = "Project ID", required = true)
        var projectId: Long,
        @ApiParam(value = "중기 목표")
        val middleGoal: String,
        @ApiParam(value = "목표일")
        val dueDate: LocalDateTime,
        @ApiModelProperty(
                name = "생성일"
        )
        @ApiParam(value = "생성일")
        val createdAt: LocalDateTime
        )
