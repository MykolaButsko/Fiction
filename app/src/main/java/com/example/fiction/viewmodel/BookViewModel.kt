package com.example.fiction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fiction.dataClasses.Book

class BookViewModel(private val bookRepository: BookRepository) : ViewModel() {

    private val _bookList = MutableLiveData<List<Book>>()
    val bookListLiveData: LiveData<List<Book>> = _bookList

    fun loadBook() {
        _bookList.value = bookRepository.getBooks()
    }

    fun filterBooksByGenre(genre: String) {
        val filteredBooks = bookRepository.getBooksByGenre(genre)
        _bookList.value = filteredBooks
    }

    private val _favoriteListID = MutableLiveData<MutableSet<Int>>(mutableSetOf())
    val favoriteListID: LiveData<MutableSet<Int>> get() = _favoriteListID

    fun isLiked(bookID: Int) {
        val currentFavorite = _favoriteListID.value ?: mutableSetOf()
        if (currentFavorite.contains(bookID)) {
            currentFavorite.remove(bookID)
        } else {
            currentFavorite.add(bookID)
        }
        _favoriteListID.value = currentFavorite
    }
}