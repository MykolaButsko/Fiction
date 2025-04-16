package com.example.fiction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fiction.R
import com.example.fiction.dataClasses.BookDescription
import com.example.fiction.databinding.FragmentBookDescriptionBinding

class BookDescriptionFragment : Fragment() {

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

        val bookNameID = arguments?.getString("BOOK_NAME")

        val booksDescription = listOf(
            BookDescription(
                R.drawable.img_1,
                getString(R.string.name_1),
                getString(R.string.author_1),
                getString(R.string.about_this_book),
                getString(R.string.book_description_1)
            ),
            BookDescription(
                R.drawable.img_2,
                getString(R.string.name_2),
                getString(R.string.author_2),
                getString(R.string.about_this_book),
                getString(R.string.book_description_2)
            ),
            BookDescription(
                R.drawable.img_3,
                getString(R.string.name_3),
                getString(R.string.author_3),
                getString(R.string.about_this_book),
                getString(R.string.book_description_3)
            ),
            BookDescription(
                R.drawable.img_4,
                getString(R.string.name_4),
                getString(R.string.author_4),
                getString(R.string.about_this_book),
                getString(R.string.book_description_4)
            )
        )
        val selectedBook = booksDescription.find {
            it.bookName == bookNameID
        }

        if (selectedBook != null) with(bookDescriptionBinding) {
            image1.setImageResource(selectedBook.img)
            bookName1.text = selectedBook.bookName
            bookAuthor1.text = selectedBook.bookAuthor
            aboutBook.text = selectedBook.aboutBook
            bookDescription1.text = selectedBook.bookDescription
        }
    }
}