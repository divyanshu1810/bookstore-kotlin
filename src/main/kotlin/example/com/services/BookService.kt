package example.com.services

import example.com.models.Book
import example.com.repositories.BookRepository

class BookService(private val repository: BookRepository) {
    fun getAllBooks(): List<Book> = repository.getAllBooks()

    fun getBookById(id: Int): Book? = repository.getBookById(id)

    fun addBook(book: Book): Book = repository.addBook(book)

    fun updateBook(id: Int, book: Book): Boolean = repository.updateBook(id, book)

    fun deleteBook(id: Int): Boolean = repository.deleteBook(id)
}
