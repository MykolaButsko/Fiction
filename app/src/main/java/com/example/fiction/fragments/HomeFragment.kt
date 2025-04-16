package com.example.fiction.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fiction.BookAdapter
import com.example.fiction.BookDescriptionActivity
import com.example.fiction.R
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
        chooseGenreBook()

        return homeBinding.root
    }

    private fun init() {

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

    private fun chooseGenreBook() = with(homeBinding) {
        buttonFiction.setOnClickListener {
            bookViewModel.loadBook()
            textViewFiction.text = (getString(R.string.app_name))
        }

        buttonCultureAndSociety.setOnClickListener {
            bookViewModel.filterBooksByGenre(getString(R.string.culture_and_society))
            textViewFiction.text = (getString(R.string.culture_and_society))
        }

        buttonMindAndPhilosophy.setOnClickListener {
            bookViewModel.filterBooksByGenre(getString(R.string.mind_and_philosophy))
            textViewFiction.text = (getString(R.string.mind_and_philosophy))
        }

        buttonPersonalGrowth.setOnClickListener {
            bookViewModel.filterBooksByGenre(getString(R.string.personal_growth))
            textViewFiction.text = (getString(R.string.personal_growth))
        }
    }
}