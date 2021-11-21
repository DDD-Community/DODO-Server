package com.dodo.dodolistserver.project.entity

import com.dodo.dodolistserver.user.entity.User
import lombok.Getter
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Getter
@Table(name="projects")
class Project (
    @Id @GeneratedValue
    val id: Long,
    @ManyToOne
    @JoinColumn(name="user_id")
    val user: User,
    var isDaily: Boolean,
    var goal: String,
    var dueDate: LocalDateTime,
    var comment: String,
    @CreationTimestamp
    val createdAt: LocalDateTime
)