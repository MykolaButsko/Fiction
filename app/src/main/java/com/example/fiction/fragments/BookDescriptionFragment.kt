package com.example.fiction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fiction.databinding.FragmentBookDescriptionBinding

class BookDescriptionFragment : Fragment() {

    private lateinit var bookDescriptionBinding: FragmentBookDescriptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        bookDescriptionBinding = FragmentBookDescriptionBinding.inflate(layoutInflater)

        return bookDescriptionBinding.root
    }
}