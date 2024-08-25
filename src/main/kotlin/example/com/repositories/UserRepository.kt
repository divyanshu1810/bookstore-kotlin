package example.com.repositories

import at.favre.lib.crypto.bcrypt.BCrypt
import example.com.models.User
import example.com.models.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {
    fun getUserByUsername(username: String): User? = transaction {
        Users.select { Users.username eq username }
            .mapNotNull { toUser(it) }
            .singleOrNull()
    }

    fun addUser(user: User): User = transaction {
        val id = Users.insertAndGetId {
            it[username] = user.username
            it[password] = BCrypt.withDefaults().hashToString(12, user.password.toCharArray())
        }
        user.copy(id = id.value)
    }

    private fun toUser(row: ResultRow): User =
        User(
            id = row[Users.id].value,
            username = row[Users.username],
            password = row[Users.password]
        )
}
