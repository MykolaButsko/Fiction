package com.example.fiction.data.model

data class Book(
    val bookID: Int,
    val genre: String,
    val img: Int,
    val toggleFavorite: Boolean,
    val bookName: String,
    val bookAuthor: String
)