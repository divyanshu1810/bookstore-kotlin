package example.com.services

import example.com.models.User
import example.com.repositories.UserRepository

class UserService(private val repository: UserRepository) {
    fun getUserByUsername(username: String): User? = repository.getUserByUsername(username)

    fun addUser(user: User): User = repository.addUser(user)
}
