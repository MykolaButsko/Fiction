package com.example.fiction.data.repository

import android.content.Context
import com.example.fiction.R
import com.example.fiction.data.model.Book
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BookRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val booksList: MutableList<Book> = mutableListOf()

    fun getBooks(): List<Book> {
        if (booksList.isEmpty()) {
            booksList.addAll(
                listOf(
                    Book(
                        1,
                        context.getString(R.string.culture_and_society),
                        R.drawable.img_fiction,
                        false,
                        context.getString(R.string.book_the_good_guy),
                        context.getString(R.string.author_the_good_guy)
                    ),
                    Book(
                        2,
                        context.getString(R.string.culture_and_society),
                        R.drawable.img_culture_and_society,
                        false,
                        context.getString(R.string.book_norse_mythology),
                        context.getString(R.string.author_norse_mythology)
                    ),
                    Book(
                        3,
                        context.getString(R.string.mind_and_philosophy),
                        R.drawable.img_mind_and_philosophy,
                        false,
                        context.getString(R.string.book_futurama),
                        context.getString(R.string.author_futurama)
                    ),
                    Book(
                        4,
                        context.getString(R.string.personal_growth),
                        R.drawable.img_personal_growth,
                        false,
                        context.getString(R.string.book_explore_your_creative),
                        context.getString(R.string.author_explore_your_creative)
                    )
                )
            )
        }
        return booksList.toList()
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