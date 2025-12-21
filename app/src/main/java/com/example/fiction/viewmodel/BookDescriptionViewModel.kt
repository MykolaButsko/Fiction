package com.example.fiction.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fiction.data.model.BookDescription
import com.example.fiction.data.repository.BookDescriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BookDescriptionViewModel @Inject constructor(
    private val bookDescriptionRepo: BookDescriptionRepository
) : ViewModel() {

    private val _bookDescription = MutableStateFlow<BookDescription?>(null)
    val bookDescription: StateFlow<BookDescription?> = _bookDescription.asStateFlow()

    fun loadBookDescription(id: Int) {
        val description = bookDescriptionRepo.getDescriptionById(id)
        _bookDescription.value = description
    }
}