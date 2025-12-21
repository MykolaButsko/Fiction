package com.example.fiction.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fiction.databinding.FragmentLibraryBinding
import com.example.fiction.ui.adapter.BookAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                bookViewModel.favListBooks.collect { bookId ->
                    bookAdapter.submitList(bookId)
                }
            }
        }
    }
}