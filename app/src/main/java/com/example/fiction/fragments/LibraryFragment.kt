package com.example.fiction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fiction.databinding.FragmentLibraryBinding

class LibraryFragment : BaseFragment<FragmentLibraryBinding>(
    FragmentLibraryBinding::inflate
) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = bookAdapter

        bookViewModel.favListBooks.observe(viewLifecycleOwner) { bookID ->
            bookAdapter.submitList(bookID)
        }

        return binding.root
    }
}