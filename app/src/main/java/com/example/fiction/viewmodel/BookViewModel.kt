package com.example.fiction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fiction.dataClasses.Book

class BookViewModel(private val bookRepository: BookRepository) : ViewModel() {

    private val _bookList = MutableLiveData<List<Book>>()
    val bookListLiveData: LiveData<List<Book>> = _bookList

    private val _favoriteListID = MutableLiveData<MutableSet<Int>>(mutableSetOf())
    private val favoriteListID: LiveData<MutableSet<Int>> get() = _favoriteListID

    private val _favListBooks = MutableLiveData<List<Book>>(emptyList())
    val favListBooks: LiveData<List<Book>> = _favListBooks

    fun loadBook() {
        _bookList.value = bookRepository.getBooks()
    }

    fun filterBooksByGenre(genre: String) {
        val filteredBooks = bookRepository.getBooksByGenre(genre)
        _bookList.value = filteredBooks
    }

    fun isLiked(bookID: Int) {
        val currentFavorite = _favoriteListID.value ?: mutableSetOf()
        if (currentFavorite.contains(bookID)) {
            currentFavorite.remove(bookID)
        } else {
            currentFavorite.add(bookID)
        }
        _favoriteListID.value = currentFavorite
        likeBook(bookID)
        filterFavBooks()
    }

    private fun likeBook(bookID: Int) {
        val currentList = _bookList.value ?: return
        val book = currentList.firstOrNull {it.bookID == bookID} ?: return
        val newState = !book.toggleFavorite

        bookRepository.updateFavoriteBook(bookID, newState)

        _bookList.value = bookRepository.getBooks()
    }

    private fun filterFavBooks() {
        bookListLiveData.value?.let { allBooks ->
            favoriteListID.value?.let { favoriteIDs ->
                _favListBooks.value = allBooks.filter { it.bookID in favoriteIDs }
            }
        }
    }
}