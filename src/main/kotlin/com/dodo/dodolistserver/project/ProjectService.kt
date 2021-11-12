package com.dodo.dodolistserver.project

import org.springframework.stereotype.Service

@Service
class ProjectService(private val projectRepository: ProjectRepository, private val projectMapper: ProjectMapper) {

    fun createProject(project: Project): ProjectDto? {
        val createdProject = projectMapper.toDto(projectRepository.save(project))

        return createdProject
    }

    fun getProjectByProjectId(projectId: Long): ProjectDto? {
        val selectedProject = projectMapper.toDto(projectRepository.findById(projectId).get())

        return selectedProject
    }

    fun getProjectByUserId(userId: Long): List<ProjectDto> {
        val userProjects = projectMapper.toDtos(projectRepository.findByUserId(userId))

        return userProjects
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    fun editProject(project: Project): ProjectDto? {
        val existProject = projectRepository.findById(project.id)

        if(existProject.isPresent) {
            val editedProject = projectMapper.toDto(projectRepository.save(project))
            return editedProject
        }
        return null
    }
}