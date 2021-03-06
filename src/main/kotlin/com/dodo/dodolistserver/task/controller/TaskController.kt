package com.dodo.dodolistserver.task.controller

import com.dodo.dodolistserver.common.dto.ResponseDto
import com.dodo.dodolistserver.common.message.ResponseMessage
import com.dodo.dodolistserver.task.dto.CreateTaskRequestDto
import com.dodo.dodolistserver.task.dto.EditTaskRequestDto
import com.dodo.dodolistserver.task.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/tasks")
class TaskController(private val taskService: TaskService) {

    @PostMapping
    fun createTask(@RequestBody createTaskRequestDto: CreateTaskRequestDto): ResponseEntity<Any> {
        val createdTask = taskService.createTask(createTaskRequestDto)

        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, createdTask))
    }

    @GetMapping(path = ["/{taskId}"])
    fun getTaskByTaskId(@PathVariable("taskId") taskId: Long): ResponseEntity<Any> {
        val selectedTask = taskService.getTaskByTaskId(taskId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, selectedTask))
    }

    @GetMapping(path = ["/epic/{epicId}"])
    fun getEpicByProjectId(@PathVariable("epicId") epicId: Long): ResponseEntity<Any> {
        val epicTasks = taskService.getTaskByEpicId(epicId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, epicTasks))
    }

    @GetMapping(path = ["/user/{userId}"])
    fun getTaskByUserId(@PathVariable("userId") userId: Long): ResponseEntity<Any> {
        val userTasks = taskService.getTaskByUserId(userId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, userTasks))
    }

    @PutMapping
    fun editTask(@RequestBody editTaskRequestDto: EditTaskRequestDto): ResponseEntity<Any> {
        val editedTask = taskService.editTask(editTaskRequestDto)

        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, editedTask))
    }
}