package example.com.config

import io.ktor.server.application.*
import io.ktor.server.sessions.*

fun Application.configureSessions() {
    install(Sessions) {
        cookie<UserSession>("user_session")
    }
}

data class UserSession(val userId: String)
