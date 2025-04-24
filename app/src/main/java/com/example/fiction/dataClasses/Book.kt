package com.example.fiction.dataClasses

data class Book(
    val bookID: Int,
    val genre: String,
    val img: Int,
    val toggleFavorite: Boolean,
    val bookName: String,
    val bookAuthor: String
)