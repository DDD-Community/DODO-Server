package com.dodo.dodolistserver.project.controller

import com.dodo.dodolistserver.common.dto.ResponseDto
import com.dodo.dodolistserver.common.message.ResponseMessage
import com.dodo.dodolistserver.project.dto.ProjectDto
import com.dodo.dodolistserver.project.service.ProjectService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/projects")
class ProjectController(private val projectService: ProjectService) {

    @PostMapping
    fun createProject(@RequestBody projectDto: ProjectDto): ResponseEntity<Any> {
        val createdProject = projectService.createProject(projectDto)

        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, createdProject))
    }

    @GetMapping(path = ["/{projectId}"])
    fun getProjectByProjectId(@PathVariable("projectId") projectId: Long): ResponseEntity<Any> {
        val selectedProject = projectService.getProjectByProjectId(projectId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, selectedProject))
    }

    @GetMapping(path = ["/user/{userId}"])
    fun getProjectByUserId(@PathVariable("userId") userId: Long): ResponseEntity<Any> {
        val userProjects = projectService.getProjectByUserId(userId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, userProjects))
    }

    @PutMapping
    fun editProject(@RequestBody projectDto: ProjectDto): ResponseEntity<Any> {
        val editedProject = projectService.editProject(projectDto)

        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, editedProject))
    }

}