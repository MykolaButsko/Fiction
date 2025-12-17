package com.example.fiction.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.fiction.databinding.FragmentBookDescriptionBinding
import com.example.fiction.viewmodel.BookDescriptionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDescriptionFragment : BaseFragment<FragmentBookDescriptionBinding>(
    FragmentBookDescriptionBinding::inflate
) {
    private val bookDescriptionViewModel: BookDescriptionViewModel by viewModels()
    private val args: BookDescriptionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = args.bookId

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