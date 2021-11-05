package com.dodo.dodolistserver.project

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings


@Mapper(componentModel = "spring")
interface ProjectMapper {
    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "userId", ignore = true)
    )
    fun toEntity(projectDto: ProjectDto) : Project

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "userId", ignore = true)
    )
    fun toEntities(projectDtos: List<ProjectDto>) : List<Project>

    fun toDto(project: Project) : ProjectDto

    fun toDtos(projects: List<Project>) : List<ProjectDto>
}