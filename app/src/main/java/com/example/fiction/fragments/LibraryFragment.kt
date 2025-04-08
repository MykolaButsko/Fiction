package com.example.fiction.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fiction.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {

    private lateinit var libraryBinding: FragmentLibraryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        libraryBinding = FragmentLibraryBinding.inflate(layoutInflater)
        return libraryBinding.root
    }
}