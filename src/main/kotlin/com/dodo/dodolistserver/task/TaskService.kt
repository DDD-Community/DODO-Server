package com.dodo.dodolistserver.task

import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository, private val taskMapper: TaskMapper) {

    fun createTask(taskDto: TaskDto): TaskDto? {
        val taskEntity = taskMapper.toEntity(taskDto)
        val createdTask = taskMapper.toDto(taskRepository.save(taskEntity))

        return createdTask
    }

    fun getTaskByTaskId(taskId: Long): TaskDto? {
        val selectedTask = taskMapper.toDto(taskRepository.findById(taskId).get())

        return selectedTask
    }

    fun getTaskByEpicId(epicId: Long): List<TaskDto> {
        val epicTasks = taskMapper.toDtos(taskRepository.findByEpicId(epicId))

        return epicTasks
    }

    fun getTaskByUserId(userId: Long): List<TaskDto> {
        val userTasks = taskMapper.toDtos(taskRepository.findByUserId(userId))

        return userTasks
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    fun editTask(taskDto: TaskDto): TaskDto? {
        val taskEntity = taskMapper.toEntity(taskDto)
        val existTask = taskRepository.findById(taskEntity.id)

        if(existTask.isPresent) {
            val editedTask = taskMapper.toDto(taskRepository.save(taskEntity))

            return editedTask
        }
        return null
    }

}