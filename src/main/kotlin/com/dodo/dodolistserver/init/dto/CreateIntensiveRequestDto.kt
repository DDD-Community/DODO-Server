package com.dodo.dodolistserver.init.dto

import com.dodo.dodolistserver.epic.dto.CreateEpicRequestDto
import com.dodo.dodolistserver.project.dto.CreateProjectRequestDto
import lombok.Builder
import lombok.Getter

@Getter
@Builder
class CreateIntensiveRequestDto (
    val createProjectRequestDto: CreateProjectRequestDto,
    val createEpicRequestDtos: List<CreateEpicRequestDto>
)