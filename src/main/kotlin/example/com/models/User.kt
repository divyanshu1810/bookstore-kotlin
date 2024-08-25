package example.com.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable() {
    val username = varchar("username", 255).uniqueIndex()
    val password = varchar("password", 255)
}

data class User(
    val id: Int?,
    val username: String,
    val password: String
)
