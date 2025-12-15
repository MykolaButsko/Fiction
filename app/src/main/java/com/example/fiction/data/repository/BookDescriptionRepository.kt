package com.example.fiction.data.repository

import com.example.fiction.data.model.BookDescription
import com.example.fiction.data.resources.AppResources
import javax.inject.Inject

class BookDescriptionRepository @Inject constructor(
    private val resources: AppResources
) {
    fun getDescriptionById(id: Int): BookDescription? {
        val index = id - 1
        if (index !in resources.titles.indices) return null

        return BookDescription(
            bookId = index,
            imageRes = resources.bookImages[index],
            title = resources.titles[index],
            author = resources.authors[index],
            aboutBook = resources.aboutBook,
            description = resources.descriptions[index]
        )
    }
}