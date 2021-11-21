package com.dodo.dodolistserver.epic.dto

import com.dodo.dodolistserver.epic.entity.Epic
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface EpicMapper {
    @Mappings(
        Mapping(target = "id", ignore = true)
    )
    fun fromCreateRequestToEntity(createEpicRequestDto: CreateEpicRequestDto) : Epic

    @Mappings(
            Mapping(target = "project", ignore = true)
    )
    fun fromEditRequestToEntity(editEpicRequestDto: EditEpicRequestDto) : Epic

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "project", ignore = true)
    )
    fun toEntities(epics: List<EpicResponseDto>) : List<Epic>

    fun toResponseDto(epic: Epic) : EpicResponseDto

    fun toResponseDtos(epics: List<Epic>) : List<EpicResponseDto>
}