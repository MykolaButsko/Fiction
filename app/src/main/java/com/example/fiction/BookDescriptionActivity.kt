package com.example.fiction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fiction.databinding.ActivityBookDescriptionBinding
import com.example.fiction.fragments.BookDescriptionFragment

class BookDescriptionActivity : AppCompatActivity() {

    private lateinit var activityBookDescription: ActivityBookDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBookDescription = ActivityBookDescriptionBinding.inflate(layoutInflater)
        setContentView(activityBookDescription.root)

        if (savedInstanceState == null) {
            val bookName = intent.getStringExtra("BOOK_NAME")

            val bookDescriptionFragment = BookDescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString("BOOK_NAME", bookName)
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayoutBookDescriptionActivity, bookDescriptionFragment)
                .commit()
        }
    }
}