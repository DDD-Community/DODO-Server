package com.dodo.dodolistserver.Project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/projects")
class ProjectController(private val projectService: ProjectService) {

    @PostMapping
    fun createProject(@RequestBody project: Project): ResponseEntity<Any> {
        val createdProject = projectService.createProject(project)

        return ResponseEntity.ok().body(createdProject)
    }

    @GetMapping(path = ["/{projectId}"])
    fun getProjectByProjectId(@PathVariable("projectId") projectId: Long): ResponseEntity<Any> {
        val selectedProject = projectService.getProjectByProjectId(projectId)
        return ResponseEntity.ok().body(selectedProject)
    }

    @GetMapping(path = ["/user/{userId}"])
    fun getProjectByUserId(@PathVariable("userId") userId: Long): ResponseEntity<Any> {
        val userProjects = projectService.getProjectByUserId(userId)
        return ResponseEntity.ok().body(userProjects)
    }

    @PutMapping
    fun editProject(@RequestBody project: Project): ResponseEntity<Any> {
        val editedProject = projectService.editProject(project)

        return ResponseEntity.ok().body(editedProject)
    }

}