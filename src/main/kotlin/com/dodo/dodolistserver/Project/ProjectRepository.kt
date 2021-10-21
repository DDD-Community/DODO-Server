package com.dodo.dodolistserver.Project

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Long> {
    fun findByUserId(userId: Long): List<Project>
}