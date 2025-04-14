package com.example.fiction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fiction.databinding.ActivityBookDescriptionBinding

class BookDescriptionActivity : AppCompatActivity() {

    private lateinit var activityBookDescription: ActivityBookDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBookDescription = ActivityBookDescriptionBinding.inflate(layoutInflater)
        setContentView(activityBookDescription.root)
    }
}