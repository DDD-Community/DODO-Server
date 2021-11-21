package com.dodo.dodolistserver.epic.entity

import com.dodo.dodolistserver.project.entity.Project
import com.dodo.dodolistserver.user.entity.User
import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Getter
@Setter
@Table(name="epics")
class Epic (
    @Id
    @GeneratedValue
    val id: Long,
    @ManyToOne
    @JoinColumn(name="project_id")
    val project: Project,
    @ManyToOne
    @JoinColumn(name="user_id")
    val user: User,
    var middleGoal: String,
    var dueDate: LocalDateTime,
    @CreationTimestamp
    var createdAt: LocalDateTime
)