package com.dodo.dodolistserver.user.entity

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name="user")
open class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

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
