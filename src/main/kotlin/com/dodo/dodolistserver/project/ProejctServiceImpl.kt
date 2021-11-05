package com.dodo.dodolistserver.project

import org.springframework.stereotype.Service

@Service
class ProejctServiceImpl(private val projectRepository: ProjectRepository, private val projectMapper: ProjectMapper): ProjectService {

    override fun createProject(project: Project): ProjectDto? {
        val createdProject = projectMapper.toDto(projectRepository.save(project))

        return createdProject
    }

    override fun getProjectByProjectId(projectId: Long): ProjectDto? {
        val selectedProject = projectMapper.toDto(projectRepository.findById(projectId).get())

        return selectedProject
    }

    override fun getProjectByUserId(userId: Long): List<ProjectDto> {
        val userProjects = projectMapper.toDtos(projectRepository.findByUserId(userId))

        return userProjects
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor=[Exception::class])
    override fun editProject(project: Project): ProjectDto? {
        val existProject = projectRepository.findById(project.id)

        if(existProject.isPresent) {
            val editedProject = projectMapper.toDto(projectRepository.save(project))
            return editedProject
        }
        return null
    }
}