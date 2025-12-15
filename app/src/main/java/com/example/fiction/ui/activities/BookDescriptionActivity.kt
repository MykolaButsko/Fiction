package com.example.fiction.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fiction.R
import com.example.fiction.databinding.ActivityBookDescriptionBinding
import com.example.fiction.ui.fragments.BookDescriptionFragment
import com.example.fiction.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDescriptionActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context, bookId: Int): Intent {
            return Intent(context, BookDescriptionActivity::class.java).apply {
                putExtra(Constants.ARG_BOOK_ID, bookId)
            }
        }
    }

    private lateinit var activityBookDescription: ActivityBookDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBookDescription = ActivityBookDescriptionBinding.inflate(layoutInflater)
        setContentView(activityBookDescription.root)

        if (savedInstanceState == null) {
            val bookId = intent.getIntExtra(Constants.ARG_BOOK_ID, -1)

            val bookDescriptionFragment = BookDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.ARG_BOOK_ID, bookId)
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayoutBookDescriptionActivity, bookDescriptionFragment)
                .commit()
        }
    }
}