package com.example.fiction.data.repository

import com.example.fiction.data.model.Book
import com.example.fiction.data.model.Genre
import com.example.fiction.data.resources.AppResources
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val resources: AppResources
) {
    private val bookList: MutableList<Book> = mutableListOf()

    fun getBooks(): List<Book> {
        if (bookList.isEmpty()) {
            bookList.addAll(
                resources.titles.mapIndexed { index, title ->
                    Book(
                        bookId = index + 1,
                        genre = resources.bookGenres[index],
                        imageRes = resources.bookImages[index],
                        isFavorite = false,
                        title = title,
                        author = resources.authors[index]
                    )
                }
            )
        }
        return bookList.toList()
    }

    fun getBookByGenre(genreBook: Genre): List<Book> {
        return bookList.filter { it.genre == genreBook }
    }

    fun updateFavoriteBook(bookId: Int, isFavorite: Boolean) {
        val index = bookList.indexOfFirst { it.bookId == bookId }
        if (index != -1) {
            val updatedBook = bookList[index].copy(isFavorite = isFavorite)
            bookList[index] = updatedBook
        }
    }
}