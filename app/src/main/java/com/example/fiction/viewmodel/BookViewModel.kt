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
        _bookList.value = _bookList.value!!.map { book ->
            if (book.bookID == bookID) book.copy(likeBook = book.likeBook) else book
        }
    }

    private fun filterFavBooks() {
        _favListBooks.value =
            bookListLiveData.value!!.filter { it.bookID in favoriteListID.value!! }
    }
}