package com.dodo.dodolistserver.init.dto

import com.dodo.dodolistserver.epic.dto.CreateEpicRequestDto
import com.dodo.dodolistserver.epic.dto.EpicResponseDto
import com.dodo.dodolistserver.project.dto.CreateProjectRequestDto
import com.dodo.dodolistserver.project.dto.ProjectResponseDto

class InitIntensivelyResponseDto (
    val projectResponseDto: ProjectResponseDto,
    val epicResponseDtos: List<EpicResponseDto>
)