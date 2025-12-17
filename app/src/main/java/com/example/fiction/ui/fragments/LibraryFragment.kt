package com.example.fiction.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fiction.databinding.FragmentLibraryBinding
import com.example.fiction.ui.adapter.BookAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LibraryFragment : BaseFragment<FragmentLibraryBinding>(
    FragmentLibraryBinding::inflate
) {
    private val bookAdapter by lazy {
        BookAdapter(
            onOpenBookDescription = { bookId ->
                findNavController().navigate(
                    LibraryFragmentDirections
                        .actionLibraryFragmentToBookDescriptionFragment(bookId)
                )
            },

            onFavoriteToggle = { bookId ->
                bookViewModel.toggleFavorite(bookId)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = bookAdapter

        updateFavList()
    }

    private fun updateFavList() {
        bookViewModel.favListBooks.observe(viewLifecycleOwner) { bookId ->
            bookAdapter.submitList(bookId)
        }
    }
}