package com.example.fiction.data.repository

import com.example.fiction.data.model.Book
import com.example.fiction.data.resources.AppResources
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val resources: AppResources
) {
    private val bookList: List<Book> =
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

    fun getBooks(): List<Book> = bookList
}