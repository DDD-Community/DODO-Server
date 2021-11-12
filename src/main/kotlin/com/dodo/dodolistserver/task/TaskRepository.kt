package com.dodo.dodolistserver.task

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
    fun findByEpicId(epicId: Long) : List<Task>
    fun findByUserId(userId: Long): List<Task>
}