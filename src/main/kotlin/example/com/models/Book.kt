package example.com.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Books : IntIdTable() {
    val title = varchar("title", 255)
    val author = varchar("author", 255)
    val price = decimal("price", 10, 2)
}

data class Book(
    val id: Int?,
    val title: String,
    val author: String,
    val price: Double
)
