package com.example.fiction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fiction.databinding.ActivityBookDescriptionBinding
import com.example.fiction.fragments.BookDescriptionFragment

class BookDescriptionActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_BOOK_NAME = "EXTRA_BOOK_NAME"

        fun createIntent(context: Context, bookName: String): Intent {
            return Intent(context, BookDescriptionActivity::class.java).apply {
                putExtra(EXTRA_BOOK_NAME, bookName)
            }
        }
    }

    private lateinit var activityBookDescription: ActivityBookDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBookDescription = ActivityBookDescriptionBinding.inflate(layoutInflater)
        setContentView(activityBookDescription.root)

        if (savedInstanceState == null) {
            val bookName = intent.getStringExtra(EXTRA_BOOK_NAME)

            val bookDescriptionFragment = BookDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(BookDescriptionFragment.ARG_BOOK_NAME, bookName)
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayoutBookDescriptionActivity, bookDescriptionFragment)
                .commit()
        }
    }
}