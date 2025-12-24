package com.example.fiction.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.fiction.databinding.FragmentBookDescriptionBinding
import com.example.fiction.viewmodel.BookDescriptionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookDescriptionFragment : BaseFragment<FragmentBookDescriptionBinding>(
    FragmentBookDescriptionBinding::inflate
) {
    private val bookDescriptionViewModel: BookDescriptionViewModel by viewModels()
    private val args: BookDescriptionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = args.bookId

        bookDescriptionViewModel.loadBookDescription(bookId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                bookDescriptionViewModel.bookDescription.collect { bookDescription ->
                    bookDescription?.let {
                        binding.bookImg.setImageResource(bookDescription.imageRes)
                        binding.bookTitle.text = bookDescription.title
                        binding.bookAuthor.text = bookDescription.author
                        binding.aboutBook.text = bookDescription.aboutBook
                        binding.bookDescription.text = bookDescription.description
                    }
                }
            }
        }
    }
}