package com.example.fiction.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fiction.data.model.BookDescription
import com.example.fiction.data.repository.BookDescriptionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookDescriptionViewModel @Inject constructor(
    private val bookDescriptionRepo: BookDescriptionRepository
) : ViewModel() {

    private val _bookDescription = MutableLiveData<BookDescription>()
    val bookDescription: LiveData<BookDescription> = _bookDescription

    fun loadBookDescription(id: Int) {
        bookDescriptionRepo.getDescriptionById(id).let {
            _bookDescription.value = it
        }
    }
}