package example.com.routes

import example.com.models.Book
import example.com.services.BookService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.bookRoutes(bookService: BookService) {
    authenticate {
        route("/books") {
            get {
                call.respond(bookService.getAllBooks())
            }

            get("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond("Invalid ID")
                    return@get
                }
                val book = bookService.getBookById(id)
                if (book == null) {
                    call.respond("Book not found")
                } else {
                    call.respond(book)
                }
            }

            post {
                val book = call.receive<Book>()
                call.respond(bookService.addBook(book))
            }

            put("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond("Invalid ID")
                    return@put
                }
                val book = call.receive<Book>()
                val updated = bookService.updateBook(id, book)
                if (updated) {
                    call.respond("Book updated")
                } else {
                    call.respond("Book not found")
                }
            }

            delete("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond("Invalid ID")
                    return@delete
                }
                val deleted = bookService.deleteBook(id)
                if (deleted) {
                    call.respond("Book deleted")
                } else {
                    call.respond("Book not found")
                }
            }
        }
    }
}
