package com.dodo.dodolistserver.project.service

import com.dodo.dodolistserver.project.dto.CreateProjectRequestDto
import com.dodo.dodolistserver.project.dto.EditProjectRequestDto
import com.dodo.dodolistserver.project.entity.Project
import com.dodo.dodolistserver.project.dto.ProjectMapper
import com.dodo.dodolistserver.project.repository.ProjectRepository
import com.dodo.dodolistserver.project.dto.ProjectResponseDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProjectService(private val projectRepository: ProjectRepository, private val projectMapper: ProjectMapper) {

    fun createProject(createProjectRequestDto: CreateProjectRequestDto): ProjectResponseDto? {

        val projectEntity = projectMapper.fromCreateRequestToEntity(createProjectRequestDto)
        val createdProjectResponse = projectMapper.toResponseDto(projectRepository.save(projectEntity))

        return createdProjectResponse
    }

    fun getProjectByProjectId(projectId: Long): ProjectResponseDto? {
        val selectedProject = projectMapper.toResponseDto(projectRepository.findById(projectId).get())

        return selectedProject
    }

    fun getProjectByUserId(userId: Long): List<ProjectResponseDto> {
        val userProjects = projectMapper.toResponseDtos(projectRepository.findByUserId(userId))

        return userProjects
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    fun editProject(editProjectRequestDto: EditProjectRequestDto): ProjectResponseDto? {

        var existingProject = projectRepository.findByIdOrNull(editProjectRequestDto.id) ?: throw RuntimeException("")

        existingProject.goal = editProjectRequestDto.goal
        existingProject.dueDate = editProjectRequestDto.dueDate
        existingProject.comment = editProjectRequestDto.comment
        existingProject.isDaily = editProjectRequestDto.isDaily

        val projectResponseDto = projectMapper.toResponseDto(existingProject)

        return projectResponseDto
    }
}