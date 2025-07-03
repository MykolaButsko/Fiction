package com.example.fiction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fiction.dataClasses.Book
import com.example.fiction.databinding.BookItemBinding

class BookAdapter(
    private val onOpenBookDescription: (Book) -> Unit,
    private val onFavoriteToggle: (Int) -> Unit
) : ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    inner class BookViewHolder(private val binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) = with(binding) {

            image1.setImageResource(book.img)

            val icon = if (book.toggleFavorite) {
                R.drawable.like_filled
            } else {
                R.drawable.like_empty
            }

            favoriteIcon.setImageResource(icon)

            bookName1.text = book.bookName
            bookAuthor1.text = book.bookAuthor

            image1.setOnClickListener {
                onOpenBookDescription(book)
            }

            favoriteIcon.setOnClickListener {
                onFavoriteToggle(book.bookID)
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