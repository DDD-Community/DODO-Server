package com.dodo.dodolistserver.Task

interface TaskService {
    fun createTask(task: Task): TaskDto?
    fun getTaskByTaskId(taskId: Long): TaskDto?
    fun getTaskByEpicId(epicId: Long): List<TaskDto>
    fun getTaskByUserId(userId: Long): List<TaskDto>
    fun editTask(task: Task): TaskDto?
}