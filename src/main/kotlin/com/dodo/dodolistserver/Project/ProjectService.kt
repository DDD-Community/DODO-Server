package com.dodo.dodolistserver.Project

import org.springframework.stereotype.Service

interface ProjectService {
    fun createProject(project: Project): Project?
    fun getProjectByProjectId(projectId: Long): Project?
    fun getProjectByUserId(userId: Long): List<Project>
    fun editProject(project: Project): Project?
}