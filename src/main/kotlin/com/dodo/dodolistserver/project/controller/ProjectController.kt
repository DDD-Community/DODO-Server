package com.dodo.dodolistserver.project.controller

import com.dodo.dodolistserver.common.dto.ResponseDto
import com.dodo.dodolistserver.common.message.ResponseMessage
import com.dodo.dodolistserver.project.dto.CreateProjectRequestDto
import com.dodo.dodolistserver.project.dto.EditProjectRequestDto
import com.dodo.dodolistserver.project.service.ProjectService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/projects")
class ProjectController(private val projectService: ProjectService) {

    @PostMapping
    @ApiOperation(value = "Project 생성", notes = "신규 Project를 생성한다. (최종 목표)")
    fun createProject(@RequestBody createProjectRequestDto: CreateProjectRequestDto): ResponseEntity<Any> {
        val createdProject = projectService.createProject(createProjectRequestDto)

        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, createdProject))
    }

    @GetMapping(path = ["/{projectId}"])
    @ApiOperation(value = "Project 조회 - Project ID", notes = "Project ID를 기반으로 Project를 검색한다.")
    fun getProjectByProjectId(@PathVariable("projectId") projectId: Long): ResponseEntity<Any> {
        val selectedProject = projectService.getProjectByProjectId(projectId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, selectedProject))
    }

    @GetMapping(path = ["/user/{userId}"])
    @ApiOperation(value = "Project 조회 - User ID", notes = "User ID를 기반으로 Project를 검색한다.")
    fun getProjectByUserId(@PathVariable("userId") userId: Long): ResponseEntity<Any> {
        val userProjects = projectService.getProjectByUserId(userId)
        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, userProjects))
    }

    @PutMapping
    @ApiOperation(value = "Project 수정", notes = "Project를 수정한다.")
    fun editProject(@RequestBody editProjectRequestDto: EditProjectRequestDto): ResponseEntity<Any> {
        val editedProject = projectService.editProject(editProjectRequestDto)

        return ResponseEntity.ok()
            .body(ResponseDto.of(HttpStatus.OK, ResponseMessage.SUCCESS, editedProject))
    }

}