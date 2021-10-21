package com.dodo.dodolistserver.Project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProejctServiceImpl: ProjectService {

    @Autowired
    private lateinit var projectRepository: ProjectRepository;

    override fun createProject(project: Project): Project? {
        val createdProject = projectRepository.save(project)

        return createdProject
    }

    override fun getProjectByProjectId(projectId: Long): Project? {
        val selectedProject = projectRepository.findById(projectId).get()

        return selectedProject
    }

    override fun getProjectByUserId(userId: Long): List<Project> {
        val userProjects = projectRepository.findByUserId(userId)

        return userProjects
    }

    override fun editProject(project: Project): Project? {
        val existProject = projectRepository.findById(project.id)

        if(existProject.isPresent) {
            projectRepository.save(project)
            return project
        }
        return null
    }
}