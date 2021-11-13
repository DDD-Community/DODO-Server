package com.dodo.dodolistserver.epic.dto

import com.dodo.dodolistserver.epic.entity.Epic
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface EpicMapper {
    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "projectId", ignore = true)
    )
    fun toEntity(epicDto: EpicDto) : Epic

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "projectId", ignore = true)
    )
    fun toEntities(epics: List<EpicDto>) : List<Epic>

    fun toDto(epic: Epic) : EpicDto

    fun toDtos(epics: List<Epic>) : List<EpicDto>
}