package com.dodo.dodolistserver.task.controller

import com.dodo.dodolistserver.common.dto.ResponseDto
import com.dodo.dodolistserver.common.message.ResponseMessage
import com.dodo.dodolistserver.task.dto.CreateTaskRequestDto
import com.dodo.dodolistserver.task.dto.EditTaskRequestDto
import com.dodo.dodolistserver.task.service.TaskService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/tasks")
class TaskController(private val taskService: TaskService) {

    @PostMapping
    @ApiOperation(value = "Task 생성", notes = "신규 Task를 생성한다. (단기 목표, Epic 하위)")
    fun createTask(@RequestBody createTaskRequestDto: CreateTaskRequestDto): ResponseEntity<Any> {
        val createdTask = taskService.createTask(createTaskRequestDto)

        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, createdTask))
    }

    @GetMapping(path = ["/{taskId}"])
    @ApiOperation(value = "Task 조회 - Task ID", notes = "Task ID를 기반으로 Task를 검색한다.")
    fun getTaskByTaskId(@PathVariable("taskId") taskId: Long): ResponseEntity<Any> {
        val selectedTask = taskService.getTaskByTaskId(taskId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, selectedTask))
    }

    @GetMapping(path = ["/epic/{epicId}"])
    @ApiOperation(value = "Task 조회 - Epic ID", notes = "Epic ID를 기반으로 Task를 검색한다.")
    fun getEpicByProjectId(@PathVariable("epicId") epicId: Long): ResponseEntity<Any> {
        val epicTasks = taskService.getTaskByEpicId(epicId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, epicTasks))
    }

    @GetMapping(path = ["/user/{userId}"])
    @ApiOperation(value = "Task 조회 - User ID", notes = "User ID를 기반으로 Task를 검색한다.")
    fun getTaskByUserId(@PathVariable("userId") userId: Long): ResponseEntity<Any> {
        val userTasks = taskService.getTaskByUserId(userId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, userTasks))
    }

    @PutMapping
    @ApiOperation(value = "Task 수정", notes = "Task를 수정한다.")
    fun editTask(@RequestBody editTaskRequestDto: EditTaskRequestDto): ResponseEntity<Any> {
        val editedTask = taskService.editTask(editTaskRequestDto)

        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, editedTask))
    }
}