package com.example.fiction.data.model

data class Book(
    val bookId: Int,
    val genre: String,
    val imageRes: Int,
    val isFavorite: Boolean,
    val title: String,
    val author: String
)