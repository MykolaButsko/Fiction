package com.example.fiction.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fiction.R
import com.example.fiction.data.model.Book
import com.example.fiction.databinding.BookItemBinding

class BookAdapter(
    private val onOpenBookDescription: (Book) -> Unit,
    private val onFavoriteToggle: (Int) -> Unit
) : ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    inner class BookViewHolder(private val binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) = with(binding) {

            bookImg.setImageResource(book.imageRes)

            val icon = if (book.isFavorite) {
                R.drawable.ic_favorite_filled
            } else {
                R.drawable.ic_favorite_border
            }

            favoriteToggle.setImageResource(icon)

            bookTitle.text = book.title
            bookAuthor.text = book.author

            bookImg.setOnClickListener {
                onOpenBookDescription(book)
            }

            favoriteToggle.setOnClickListener {
                onFavoriteToggle(book.bookId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}