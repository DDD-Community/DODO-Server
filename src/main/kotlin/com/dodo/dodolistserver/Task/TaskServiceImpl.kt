package com.dodo.dodolistserver.Task

import com.dodo.dodolistserver.Task.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl : TaskService{

    @Autowired
    private lateinit var taskRepository: TaskRepository

    override fun createTask(task: Task): Task? {
        val createdTask = taskRepository.save(task)

        return createdTask
    }

    override fun getTaskByTaskId(taskId: Long): Task? {
        val selectedTask = taskRepository.findById(taskId).get()

        return selectedTask
    }

    override fun getTaskByEpicId(epicId: Long): List<Task> {
        val epicTasks = taskRepository.findByEpicId(epicId)

        return epicTasks
    }

    override fun getTaskByUserId(userId: Long): List<Task> {
        val userTasks = taskRepository.findByUserId(userId)

        return userTasks
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    override fun editTask(task: Task): Task? {
        val existTask = taskRepository.findById(task.id)

        if(existTask.isPresent) {
            taskRepository.save(task)
            return task
        }
        return null
    }

}