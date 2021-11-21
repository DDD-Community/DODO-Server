package com.dodo.dodolistserver.task.dto

import com.dodo.dodolistserver.task.entity.Task
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface TaskMapper {
    @Mappings(
        Mapping(target = "id", ignore = true)
    )
    fun fromCreateRequestToEntity(createTaskRequestDto: CreateTaskRequestDto) : Task

    @Mappings(
            Mapping(target = "epic", ignore = true),
            Mapping(target = "user", ignore = true)
    )
    fun fromEditRequestToEntity(editTaskRequestDto: EditTaskRequestDto) : Task

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "epic", ignore = true),
        Mapping(target = "user", ignore = true)
    )
    fun toEntities(taskResponseDtos: List<TaskResponseDto>) : List<Task>

    fun toResponseDto(task: Task) : TaskResponseDto

    fun toResponseDtos(tasks: List<Task>) : List<TaskResponseDto>
}