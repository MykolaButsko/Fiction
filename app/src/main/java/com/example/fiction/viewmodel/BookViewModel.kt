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
}