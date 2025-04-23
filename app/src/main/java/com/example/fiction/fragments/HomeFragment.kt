package com.example.fiction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fiction.R
import com.example.fiction.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        init()
        chooseGenreBook()

        return binding.root
    }

    private fun init() {

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = bookAdapter

        observeAndUpdateBooks()

        bookViewModel.loadBook()
    }

    private fun observeAndUpdateBooks() {
        bookViewModel.bookListLiveData.observe(viewLifecycleOwner) { books ->
            bookAdapter.submitList(books)
        }
    }

    private fun chooseGenreBook() = with(binding) {
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