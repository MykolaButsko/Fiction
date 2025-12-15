package com.example.fiction.data.resources

import android.content.Context
import com.example.fiction.R
import com.example.fiction.data.model.Genre
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppResources @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val titles: Array<String>
        get() = context.resources.getStringArray(R.array.book_titles)

    val authors: Array<String>
        get() = context.resources.getStringArray(R.array.book_authors)

    val descriptions: Array<String>
        get() = context.resources.getStringArray(R.array.book_descriptions)

    val aboutBook: String
        get() = context.getString(R.string.about_this_book)

    val bookGenres = arrayOf(
        Genre.FICTION,
        Genre.CULTURE,
        Genre.MIND,
        Genre.GROWTH
    )

    val bookImages = arrayOf(
        R.drawable.img_the_good_guy,
        R.drawable.img_norse_mythology,
        R.drawable.img_futurama,
        R.drawable.img_explore_your_creative,
    )
}