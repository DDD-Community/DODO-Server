package com.dodo.dodolistserver.user.entity

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
    val id: Long,

    @Column(name = "email")
    val email: String,

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type: UserType,

    val nickname: String,

    val password: String,

    val birth: String,

    val createdDate: Timestamp,

    var lastLogin: Timestamp

    /*
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val tops

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val mids

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val bots
     */
) {
    fun updateLastLogin(): String {
        return ""
    }
}
