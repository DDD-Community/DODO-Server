package com.dodo.dodolistserver.project.service

import com.dodo.dodolistserver.project.dto.ProjectDto
import com.dodo.dodolistserver.project.dto.ProjectMapper
import com.dodo.dodolistserver.project.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(private val projectRepository: ProjectRepository, private val projectMapper: ProjectMapper) {

    fun createProject(projectDto: ProjectDto): ProjectDto? {
        val projectEntity = projectMapper.toEntity(projectDto)
        val createdProject = projectMapper.toDto(projectRepository.save(projectEntity))

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
    fun editProject(projectDto: ProjectDto): ProjectDto? {
        val projectEntity = projectMapper.toEntity(projectDto)
        val existProject = projectRepository.findById(projectEntity.id)

        if(existProject.isPresent) {
            val editedProject = projectMapper.toDto(projectRepository.save(projectEntity))
            return editedProject
        }
        return null
    }
}