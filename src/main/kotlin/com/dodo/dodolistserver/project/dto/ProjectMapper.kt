package com.dodo.dodolistserver.project.dto

import com.dodo.dodolistserver.project.entity.Project
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings


@Mapper(componentModel = "spring")
interface ProjectMapper {
    @Mappings(
        Mapping(target = "id", ignore = true)
    )
    fun fromCreateRequestToEntity(createProjectRequestDto: CreateProjectRequestDto) : Project

    @Mappings(
            Mapping(target = "user", ignore = true)
    )
    fun fromEditRequestToEntity(editProjectRequestDto: EditProjectRequestDto) : Project

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "user", ignore = true)
    )
    fun toEntities(projectResponseDtos: List<ProjectResponseDto>) : List<Project>

    fun toResponseDto(project: Project) : ProjectResponseDto

    fun toResponseDtos(projects: List<Project>) : List<ProjectResponseDto>
}