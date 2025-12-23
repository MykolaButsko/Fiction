package com.example.fiction.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiction.data.model.Book
import com.example.fiction.data.model.Genre
import com.example.fiction.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _bookList = MutableStateFlow<List<Book>>(emptyList())
    private val _favoriteId = MutableStateFlow<Set<Int>>(mutableSetOf())
    private val _currentGenre = MutableStateFlow(Genre.ALL)
    val currentGenre: StateFlow<Genre> = _currentGenre

    val homeBooks: StateFlow<List<Book>> =
        combine(_bookList, _favoriteId, _currentGenre) { books, favIds, genre ->

            books
                .filter { genre == Genre.ALL || it.genre == genre }
                .map { book ->
                    book.copy(isFavorite = favIds.contains(book.bookId))
                }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    val libraryBooks = combine(_bookList, _favoriteId) { books, favIds ->
        books
            .filter { it.bookId in favIds }
            .map { it.copy(isFavorite = true) }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )

    fun loadBook() {
        _bookList.value = bookRepository.getBooks()
    }

    fun toggleFavorite(bookId: Int) {
        _favoriteId.update { ids ->
            if (ids.contains(bookId)) ids - bookId else ids + bookId
        }
    }

    fun selectGenre(genre: Genre) {
        _currentGenre.value = genre
    }
}