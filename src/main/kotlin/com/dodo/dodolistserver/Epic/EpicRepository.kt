package com.dodo.dodolistserver.Epic

import com.dodo.dodolistserver.Project.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EpicRepository : JpaRepository<Epic, Long> {
    fun findByProjectId(projectId: Long) : List<Epic>
}