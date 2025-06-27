package com.example.fiction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
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

    private var activeButton: Button? = null

    private fun chooseGenreBook() = with(binding) {

        val allButtons = listOf(
            binding.buttonFiction,
            binding.buttonCultureAndSociety,
            binding.buttonMindAndPhilosophy,
            binding.buttonPersonalGrowth
        )

        allButtons.forEach { button ->
            button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray))
        }

        allButtons.forEach { button ->
            button.setOnClickListener {
                setActiveButton(button)
                when (button) {
                    buttonFiction -> {
                        bookViewModel.loadBook()
                        textViewFiction.text = (getString(R.string.app_name))
                    }

                    buttonCultureAndSociety -> {
                        bookViewModel.filterBooksByGenre(getString(R.string.culture_and_society))
                        textViewFiction.text = (getString(R.string.culture_and_society))
                    }

                    buttonMindAndPhilosophy -> {
                        bookViewModel.filterBooksByGenre(getString(R.string.mind_and_philosophy))
                        textViewFiction.text = (getString(R.string.mind_and_philosophy))
                    }

                    buttonPersonalGrowth -> {
                        bookViewModel.filterBooksByGenre(getString(R.string.personal_growth))
                        textViewFiction.text = (getString(R.string.personal_growth))
                    }
                }
            }
        }
        setActiveButton(buttonFiction)
    }

    private fun setActiveButton(button: Button) {

        activeButton?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.gray))
        button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_scarlet))

        activeButton = button
    }
}