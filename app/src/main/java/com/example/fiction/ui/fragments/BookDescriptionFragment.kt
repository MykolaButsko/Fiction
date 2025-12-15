package com.example.fiction.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.fiction.databinding.FragmentBookDescriptionBinding
import com.example.fiction.utils.Constants
import com.example.fiction.viewmodel.BookDescriptionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDescriptionFragment : BaseFragment<FragmentBookDescriptionBinding>(
    FragmentBookDescriptionBinding::inflate
) {
    private val bookDescriptionViewModel: BookDescriptionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = arguments?.getInt(Constants.ARG_BOOK_ID) ?: return

        bookDescriptionViewModel.bookDescription.observe(viewLifecycleOwner) { bookDescription ->
            binding.bookImg.setImageResource(bookDescription.imageRes)
            binding.bookTitle.text = bookDescription.title
            binding.bookAuthor.text = bookDescription.author
            binding.aboutBook.text = bookDescription.aboutBook
            binding.bookDescription.text = bookDescription.description
        }
        bookDescriptionViewModel.loadBookDescription(bookId)
    }
}