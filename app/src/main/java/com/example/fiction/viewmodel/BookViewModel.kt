package com.example.fiction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fiction.data.model.Book
import com.example.fiction.data.model.Genre
import com.example.fiction.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _bookList = MutableLiveData<List<Book>>()
    val bookListLiveData: LiveData<List<Book>> = _bookList

    private val _favoriteListId = MutableLiveData<MutableSet<Int>>(mutableSetOf())
    private val favoriteListId: LiveData<MutableSet<Int>> get() = _favoriteListId

    private val _favListBooks = MutableLiveData<List<Book>>(emptyList())
    val favListBooks: LiveData<List<Book>> = _favListBooks

    fun loadBook() {
        _bookList.value = bookRepository.getBooks()
    }

    private fun filterBookByGenre(genre: Genre) {
        val filteredBooks = bookRepository.getBookByGenre(genre)
        _bookList.value = filteredBooks
    }

    fun toggleFavorite(bookId: Int) {
        val currentFavorite = _favoriteListId.value ?: mutableSetOf()
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
        val currentList = _bookList.value ?: return
        val book = currentList.firstOrNull { it.bookId == id } ?: return
        val newState = !book.isFavorite

        bookRepository.updateFavoriteBook(id, newState)

        _bookList.value = bookRepository.getBooks()
    }

    private fun filterFavBook() {
        bookListLiveData.value?.let { allBooks ->
            favoriteListId.value?.let { favoriteIds ->
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