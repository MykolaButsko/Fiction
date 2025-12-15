package com.example.fiction.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fiction.data.model.Genre
import com.example.fiction.databinding.FragmentHomeBinding
import com.example.fiction.ui.activities.BookDescriptionActivity
import com.example.fiction.ui.adapter.BookAdapter
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {
    private val bookAdapter by lazy {
        BookAdapter(
            onOpenBookDescription = { bookId ->
                val intent = BookDescriptionActivity.createIntent(requireContext(), bookId)
                startActivity(intent)
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

        observeAndUpdateBooks()

        bookViewModel.loadBook()

        chooseGenreBook()
    }

    private fun observeAndUpdateBooks() {
        bookViewModel.bookListLiveData.observe(viewLifecycleOwner) { books ->
            bookAdapter.submitList(books)
        }
    }

    private fun chooseGenreBook() = with(binding) {

        val allButtons = categoriesContainer
            .children
            .filterIsInstance<MaterialButton>()

        allButtons.forEach { button ->
            button.setOnClickListener {

                val genre = Genre.fromKey(button.tag as? String)
                    ?: return@setOnClickListener

                textViewFiction.setText(genre.titleRes)
                bookViewModel.onGenreSelected(genre)
            }
        }
    }
}