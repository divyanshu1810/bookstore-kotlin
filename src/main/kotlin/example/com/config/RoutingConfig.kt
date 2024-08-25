package example.com.config

import example.com.routes.bookRoutes
import example.com.routes.userRoutes
import example.com.services.BookService
import example.com.services.UserService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(bookService: BookService, userService: UserService) {
    routing {
        userRoutes(userService)
        bookRoutes(bookService)
    }
}
