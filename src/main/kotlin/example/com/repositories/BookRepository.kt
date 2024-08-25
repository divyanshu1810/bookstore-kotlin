package example.com.repositories

import example.com.models.Book
import example.com.models.Books
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class BookRepository {
    fun getAllBooks(): List<Book> = transaction {
        Books.selectAll().map { toBook(it) }
    }

    fun getBookById(id: Int): Book? = transaction {
        Books.select { Books.id eq id }
            .mapNotNull { toBook(it) }
            .singleOrNull()
    }

    fun addBook(book: Book): Book = transaction {
        val id = Books.insertAndGetId {
            it[title] = book.title
            it[author] = book.author
            it[price] = book.price.toBigDecimal()
        }
        book.copy(id = id.value)
    }

    fun updateBook(id: Int, book: Book): Boolean = transaction {
        Books.update({ Books.id eq id }) {
            it[title] = book.title
            it[author] = book.author
            it[price] = book.price.toBigDecimal()
        } > 0
    }

    fun deleteBook(id: Int): Boolean = transaction {
        Books.deleteWhere { Books.id eq id } > 0
    }

    private fun toBook(row: ResultRow): Book =
        Book(
            id = row[Books.id].value,
            title = row[Books.title],
            author = row[Books.author],
            price = row[Books.price].toDouble()
        )
}
