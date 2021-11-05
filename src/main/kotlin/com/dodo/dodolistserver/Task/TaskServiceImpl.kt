package com.dodo.dodolistserver.Task

import com.dodo.dodolistserver.Task.Task
import lombok.NonNull
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(private val taskRepository: TaskRepository, private val taskMapper: TaskMapper) : TaskService{

    override fun createTask(task: Task): TaskDto? {
        val createdTask = taskMapper.toDto(taskRepository.save(task))

        return createdTask
    }

    override fun getTaskByTaskId(taskId: Long): TaskDto? {
        val selectedTask = taskMapper.toDto(taskRepository.findById(taskId).get())

        return selectedTask
    }

    override fun getTaskByEpicId(epicId: Long): List<TaskDto> {
        val epicTasks = taskMapper.toDtos(taskRepository.findByEpicId(epicId))

        return epicTasks
    }

    override fun getTaskByUserId(userId: Long): List<TaskDto> {
        val userTasks = taskMapper.toDtos(taskRepository.findByUserId(userId))

        return userTasks
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    override fun editTask(task: Task): TaskDto? {
        val existTask = taskRepository.findById(task.id)

        if(existTask.isPresent) {
            val editedTask = taskMapper.toDto(taskRepository.save(task))

            return editedTask
        }
        return null
    }

}