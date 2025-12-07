package com.example.fiction.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.fiction.data.model.Book

class BookDiffCallback : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.bookID == newItem.bookID
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}