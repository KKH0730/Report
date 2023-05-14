package com.example.base.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.base.extensions.binding

abstract class BaseBindingFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
) : Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = checkNotNull(_binding) {
            "Fragment $this binding cannot be accessed before onCreateView() or after onDestroyView()"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = binding(
            layoutResId = layoutResId,
            inflater = inflater,
            viewGroup = container
        )

        return binding.root
    }

    override fun onDestroyView() {
        _binding?.unbind()
        _binding = null
        super.onDestroyView()
    }
}