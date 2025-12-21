package com.example.fiction.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fiction.data.model.Book
import com.example.fiction.data.model.Genre
import com.example.fiction.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _bookList = MutableStateFlow<List<Book>>(emptyList())
    val bookList: StateFlow<List<Book>> = _bookList

    private val _favoriteListId = MutableStateFlow<MutableSet<Int>>(mutableSetOf())
    private val favoriteListId: StateFlow<MutableSet<Int>> get() = _favoriteListId

    private val _favListBooks = MutableStateFlow<List<Book>>(emptyList())
    val favListBooks: StateFlow<List<Book>> = _favListBooks

    fun loadBook() {
        _bookList.value = bookRepository.getBooks()
    }

    private fun filterBookByGenre(genre: Genre) {
        val filteredBooks = bookRepository.getBookByGenre(genre)
        _bookList.value = filteredBooks
    }

    fun toggleFavorite(bookId: Int) {
        val currentFavorite = _favoriteListId.value
        if (currentFavorite.contains(bookId)) {
            currentFavorite.remove(bookId)
        } else {
            currentFavorite.add(bookId)
        }
        _favoriteListId.value = currentFavorite
        updateFavoriteBookState(bookId)
        filterFavBook()
    }

    private fun updateFavoriteBookState(id: Int) {
        val currentList = _bookList.value
        val book = currentList.firstOrNull { it.bookId == id } ?: return
        val newState = !book.isFavorite

        bookRepository.updateFavoriteBook(id, newState)

        _bookList.value = bookRepository.getBooks()
    }

    private fun filterFavBook() {
        bookList.value.let { allBooks ->
            favoriteListId.value.let { favoriteIds ->
                _favListBooks.value = allBooks.filter { it.bookId in favoriteIds }
            }
        }
    }

    fun onGenreSelected(genre: Genre) {
        if (genre == Genre.FICTION) {
            loadBook()
        } else {
            filterBookByGenre(genre)
        }
    }
}