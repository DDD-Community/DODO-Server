package com.dodo.dodolistserver.epic.repository

import com.dodo.dodolistserver.epic.entity.Epic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EpicRepository : JpaRepository<Epic, Long> {
    fun findByProjectId(projectId: Long) : List<Epic>
}