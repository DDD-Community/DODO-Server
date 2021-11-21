package com.dodo.dodolistserver.project.repository

import com.dodo.dodolistserver.project.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Long> {
    fun findByUserId(userId: Long): List<Project>
}