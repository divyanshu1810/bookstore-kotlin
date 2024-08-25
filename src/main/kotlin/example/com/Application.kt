package example.com

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import example.com.config.*
import example.com.repositories.BookRepository
import example.com.repositories.UserRepository
import example.com.security.configureSecurity
import example.com.services.BookService
import example.com.services.UserService

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureDatabase()
    configureSecurity()
    configureRouting(BookService(BookRepository()),UserService(UserRepository()))
}
