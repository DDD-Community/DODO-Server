package com.dodo.dodolistserver.init.dto

import com.dodo.dodolistserver.epic.dto.CreateEpicRequestDto
import com.dodo.dodolistserver.epic.dto.EpicResponseDto
import com.dodo.dodolistserver.epic.entity.Epic
import com.dodo.dodolistserver.project.dto.CreateProjectRequestDto
import com.dodo.dodolistserver.project.dto.ProjectResponseDto
import com.dodo.dodolistserver.project.entity.Project
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface InitMapper {
    @Mappings(
        Mapping(target = "id", ignore = true)
    )
    fun fromCreateProjectRequestToEntity(createProjectRequestDto: CreateProjectRequestDto) : Project

    @Mappings(
        Mapping(target = "id", ignore = true)
    )
    fun fromCreateEpicRequestToEntities(createEpicRequestDtos: List<CreateEpicRequestDto>) : List<Epic>

    fun toProjectResponseDto(project: Project) : ProjectResponseDto

    fun toEpicResponseDtos(epics: List<Epic>) : List<EpicResponseDto>
}