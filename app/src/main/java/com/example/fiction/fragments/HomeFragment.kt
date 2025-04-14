package com.example.fiction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fiction.BookAdapter
import com.example.fiction.databinding.FragmentHomeBinding
import com.example.fiction.viewmodel.BookViewModel
import com.example.fiction.viewmodel.BookViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    private val bookViewModel: BookViewModel by viewModels {
        BookViewModelFactory(requireContext())
    }

    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)

        init()

        return homeBinding.root
    }

    private fun init() {
        bookAdapter = BookAdapter()

        homeBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        homeBinding.recyclerView.adapter = bookAdapter

        observeAndUpdateBooks()

        bookViewModel.loadBook()
    }

    private fun observeAndUpdateBooks() {
        bookViewModel.bookListLiveData.observe(viewLifecycleOwner) { books ->
            bookAdapter.submitList(books)
        }
    }
}