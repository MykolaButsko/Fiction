package com.example.fiction.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.example.fiction.BookAdapter
import com.example.fiction.BookDescriptionActivity
import com.example.fiction.viewmodel.BookViewModel
import com.example.fiction.viewmodel.BookViewModelFactory

abstract class BaseFragment<VBinding : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VBinding
) : Fragment() {

    protected val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory(requireContext())
    }

    protected lateinit var bookAdapter: BookAdapter

    private var _binding: VBinding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater(inflater, container, false)

        initBookAdapter()

        return binding.root
    }

    private fun initBookAdapter() {

        bookAdapter = BookAdapter(
            onOpenBookDescription = { book ->
                val intent = Intent(requireContext(), BookDescriptionActivity::class.java).apply {
                    putExtra("BOOK_NAME", book.bookName)
                }
                startActivity(intent)
            },

            onFavoriteToggle = { bookID ->
                bookViewModel.isLiked(bookID)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}