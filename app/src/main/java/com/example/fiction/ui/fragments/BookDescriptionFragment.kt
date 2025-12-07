package com.example.fiction.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fiction.R
import com.example.fiction.data.model.BookDescription
import com.example.fiction.databinding.FragmentBookDescriptionBinding

class BookDescriptionFragment : Fragment() {

    companion object {
        const val ARG_BOOK_NAME = "ARG_BOOK_NAME"
    }

    private lateinit var bookDescriptionBinding: FragmentBookDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        bookDescriptionBinding = FragmentBookDescriptionBinding.inflate(layoutInflater)

        getBooksDescription()

        return bookDescriptionBinding.root
    }

    private fun getBooksDescription() {

        val bookNameID = arguments?.getString(ARG_BOOK_NAME)

        val booksDescription = listOf(
            BookDescription(
                R.drawable.img_fiction,
                getString(R.string.book_futurama),
                getString(R.string.author_futurama),
                getString(R.string.about_this_book),
                getString(R.string.book_description_futurama)
            ),
            BookDescription(
                R.drawable.img_culture_and_society,
                getString(R.string.book_norse_mythology),
                getString(R.string.author_norse_mythology),
                getString(R.string.about_this_book),
                getString(R.string.book_description_norse_mythology)
            ),
            BookDescription(
                R.drawable.img_mind_and_philosophy,
                getString(R.string.book_futurama),
                getString(R.string.author_futurama),
                getString(R.string.about_this_book),
                getString(R.string.book_description_futurama)
            ),
            BookDescription(
                R.drawable.img_personal_growth,
                getString(R.string.book_description_explore_your_creative),
                getString(R.string.author_explore_your_creative),
                getString(R.string.about_this_book),
                getString(R.string.book_description_explore_your_creative)
            )
        )
        val selectedBook = booksDescription.find {
            it.bookName == bookNameID
        }

        if (selectedBook != null) with(bookDescriptionBinding) {
            bookImg.setImageResource(selectedBook.img)
            bookName.text = selectedBook.bookName
            bookAuthor.text = selectedBook.bookAuthor
            aboutBook.text = selectedBook.aboutBook
            bookDescription.text = selectedBook.bookDescription
        }
    }
}