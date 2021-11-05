package com.dodo.dodolistserver.Epic

import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers
import org.mapstruct.factory.Mappers.getMapper

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