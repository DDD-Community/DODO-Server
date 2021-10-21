package com.dodo.dodolistserver.Task

interface TaskService {
    fun createTask(task: Task): Task?
    fun getTaskByTaskId(taskId: Long): Task?
    fun getTaskByEpicId(epicId: Long): List<Task>
    fun getTaskByUserId(userId: Long): List<Task>
    fun editTask(task: Task): Task?
}