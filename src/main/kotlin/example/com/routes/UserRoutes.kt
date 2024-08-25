package example.com.routes

import example.com.models.User
import example.com.security.JwtConfig
import example.com.services.UserService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import at.favre.lib.crypto.bcrypt.BCrypt


fun Route.userRoutes(userService: UserService) {
    route("/users") {
        post("/register") {
            val user = call.receive<User>()
            val newUser = userService.addUser(user)
            call.respond(newUser)
        }

        post("/login") {
            val credentials = call.receive<User>()
            val user = userService.getUserByUsername(credentials.username)
            if (user != null && BCrypt.verifyer().verify(credentials.password.toCharArray(), user.password).verified) {
                val token = JwtConfig.generateToken(user.username)
                call.respond(hashMapOf("token" to token))
            } else {
                call.respond("Invalid username or password")
            }
        }
    }
}
