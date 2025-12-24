package com.example.fiction.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.example.fiction.utils.Constants
import com.example.fiction.viewmodel.BookViewModel

abstract class BaseFragment<VBinding : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VBinding
) : Fragment() {

    protected val bookViewModel: BookViewModel by activityViewModels()

    private var _binding: VBinding? = null
    protected val binding: VBinding
        get() = _binding ?: throw IllegalStateException(Constants.BINDING_DESTROYED_ERROR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}