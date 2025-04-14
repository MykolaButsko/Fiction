package com.example.fiction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fiction.dataClasses.Book
import com.example.fiction.databinding.BookItemBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var bookList = ArrayList<Book>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(book: List<Book>) {
        bookList.clear()
        bookList.addAll(book)
        notifyDataSetChanged()
    }

    class BookViewHolder(listItem: View) : RecyclerView.ViewHolder(listItem) {
        private val binding = BookItemBinding.bind(listItem)

        fun bind(book: Book) = with(binding) {
            image1.setImageResource(book.img)
            like.isChecked
            bookName1.text = book.bookName
            bookAuthor1.text = book.bookAuthor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val listItem = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return BookViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}