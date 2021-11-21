package com.dodo.dodolistserver.task.service

import com.dodo.dodolistserver.task.dto.CreateTaskRequestDto
import com.dodo.dodolistserver.task.dto.EditTaskRequestDto
import com.dodo.dodolistserver.task.entity.Task
import com.dodo.dodolistserver.task.dto.TaskResponseDto
import com.dodo.dodolistserver.task.dto.TaskMapper
import com.dodo.dodolistserver.task.repository.TaskRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository, private val taskMapper: TaskMapper) {

    fun createTask(createTaskRequestDto: CreateTaskRequestDto): TaskResponseDto? {

        val projectEntity = taskMapper.fromCreateRequestToEntity(createTaskRequestDto)
        val createdProjectResponse = taskMapper.toResponseDto(taskRepository.save(projectEntity))

        return createdProjectResponse
    }

    fun getTaskByTaskId(taskId: Long): TaskResponseDto? {
        val selectedTask = taskMapper.toResponseDto(taskRepository.findById(taskId).get())

        return selectedTask
    }

    fun getTaskByEpicId(epicId: Long): List<TaskResponseDto> {
        val epicTasks = taskMapper.toResponseDtos(taskRepository.findByEpicId(epicId))

        return epicTasks
    }

    fun getTaskByUserId(userId: Long): List<TaskResponseDto> {
        val userTasks = taskMapper.toResponseDtos(taskRepository.findByUserId(userId))

        return userTasks
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    fun editTask(editTaskRequestDto: EditTaskRequestDto): TaskResponseDto? {

        var existingTask = taskRepository.findByIdOrNull(editTaskRequestDto.id) ?: throw RuntimeException("")

        existingTask.isDone = editTaskRequestDto.isDone
        existingTask.toDo = editTaskRequestDto.toDo
        existingTask.comment = editTaskRequestDto.comment
        existingTask.priority = editTaskRequestDto.priority
        existingTask.dueDate = editTaskRequestDto.dueDate

        val taskRequestDto = taskMapper.toResponseDto(existingTask)

        return taskRequestDto
    }

}