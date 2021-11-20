package com.dodo.dodolistserver.user.entity

import com.dodo.dodolistserver.epic.entity.Epic
import com.dodo.dodolistserver.project.entity.Project
import com.dodo.dodolistserver.task.entity.Task
import org.hibernate.annotations.CreationTimestamp
import org.mindrot.jbcrypt.BCrypt
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name="user",
    uniqueConstraints = [
        UniqueConstraint(
            columnNames = ["email", "type"]
        )
    ]
)
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "email")
    val email: String,

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type: UserType,

    val name: String,

    val password: String? = null,

    val birth: String? = null,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Timestamp? = null,

    var lastLogin: Timestamp? = null,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val projects: List<Project> = emptyList(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val epics: List<Epic> = emptyList(),

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val tasks: List<Task> = emptyList(),
) {
    fun updateLastLogin() {
        this.lastLogin = Timestamp(System.currentTimeMillis())
    }

    fun checkPassword(pw: String): Boolean {
        return BCrypt.checkpw(pw, this.password!!)
    }
}
