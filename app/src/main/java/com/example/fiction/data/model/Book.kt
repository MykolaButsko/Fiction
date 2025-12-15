package com.example.fiction.data.model

data class Book(
    val bookId: Int,
    val genre: Genre,
    val imageRes: Int,
    val isFavorite: Boolean,
    val title: String,
    val author: String
)