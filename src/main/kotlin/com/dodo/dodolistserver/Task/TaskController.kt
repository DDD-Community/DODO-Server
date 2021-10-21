package com.dodo.dodolistserver.Task

import com.dodo.dodolistserver.Task.Task
import com.dodo.dodolistserver.Task.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/tasks")
class TaskController {

    @Autowired
    private lateinit var taskService: TaskService

    @PostMapping
    fun createTask(@RequestBody task: Task): ResponseEntity<Any> {
        val createdTask = taskService.createTask(task)

        return ResponseEntity.ok().body(createdTask)
    }

    @GetMapping(path = ["/{taskId}"])
    fun getTaskByTaskId(@PathVariable("taskId") taskId: Long): ResponseEntity<Any> {
        val selectedTask = taskService.getTaskByTaskId(taskId)
        return ResponseEntity.ok().body(selectedTask)
    }

    @GetMapping(path = ["/epic/{epicId}"])
    fun getEpicByProjectId(@PathVariable("epicId") epicId: Long): ResponseEntity<Any> {
        val epicTasks = taskService.getTaskByEpicId(epicId)
        return ResponseEntity.ok().body(epicTasks)
    }

    @GetMapping(path = ["/user/{userId}"])
    fun getTaskByUserId(@PathVariable("userId") userId: Long): ResponseEntity<Any> {
        val userTasks = taskService.getTaskByUserId(userId)
        return ResponseEntity.ok().body(userTasks)
    }

    @PutMapping
    fun editTask(@RequestBody task: Task): ResponseEntity<Any> {
        val editedTask = taskService.editTask(task)

        return ResponseEntity.ok().body(editedTask)
    }
}