package com.dodo.dodolistserver.Task

import com.dodo.dodolistserver.Project.Project
import com.dodo.dodolistserver.Project.ProjectDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface TaskMapper {
    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "epicId", ignore = true),
        Mapping(target = "userId", ignore = true)
    )
    fun toEntity(taskDto: TaskDto) : Task

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "epicId", ignore = true),
        Mapping(target = "userId", ignore = true)
    )
    fun toEntities(TaskDtos: List<TaskDto>) : List<Task>

    fun toDto(task: Task) : TaskDto

    fun toDtos(tasks: List<Task>) : List<TaskDto>
}