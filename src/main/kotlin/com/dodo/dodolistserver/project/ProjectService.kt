package com.dodo.dodolistserver.project

interface ProjectService {
    fun createProject(project: Project): ProjectDto?
    fun getProjectByProjectId(projectId: Long): ProjectDto?
    fun getProjectByUserId(userId: Long): List<ProjectDto>
    fun editProject(project: Project): ProjectDto?
}