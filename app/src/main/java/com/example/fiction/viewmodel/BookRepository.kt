package com.example.fiction.viewmodel

import android.content.Context
import com.example.fiction.R
import com.example.fiction.dataClasses.Book

class BookRepository(private val context: Context) {

    private val booksList: MutableList<Book> = mutableListOf()

    fun getBooks(): List<Book> {
        if (booksList.isEmpty()) {
            booksList.addAll(
                listOf(
                    Book(
                        1,
                        context.getString(R.string.app_name),
                        R.drawable.img_1,
                        false,
                        context.getString(R.string.name_1),
                        context.getString(R.string.author_1)
                    ),
                    Book(
                        2,
                        context.getString(R.string.culture_and_society),
                        R.drawable.img_2,
                        false,
                        context.getString(R.string.name_2),
                        context.getString(R.string.author_2)
                    ),
                    Book(
                        3,
                        context.getString(R.string.mind_and_philosophy),
                        R.drawable.img_3,
                        false,
                        context.getString(R.string.name_3),
                        context.getString(R.string.author_3)
                    ),
                    Book(
                        4,
                        context.getString(R.string.personal_growth),
                        R.drawable.img_4,
                        false,
                        context.getString(R.string.name_4),
                        context.getString(R.string.author_4)
                    )
                )
            )
        }
        return booksList
    }

    fun getBooksByGenre(genreBook: String): List<Book> {
        return booksList.filter { it.genre == genreBook }
    }

    fun updateFavoriteBook(bookID: Int, isFavorite: Boolean) {
        val index = booksList.indexOfFirst { it.bookID == bookID }
        if (index != -1) {
            val updatedBook = booksList[index].copy(toggleFavorite = isFavorite)
            booksList[index] = updatedBook
        }
    }
}