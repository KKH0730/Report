package com.example.base.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.example.base.extensions.binding

abstract class BaseBindingDialogFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
) : DialogFragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = checkNotNull(_binding) {
            "DialogFragment $this binding cannot be accessed before onCreateView() or after onDestroyView()"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Material_Wallpaper_NoTitleBar)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding?.unbind()
        _binding = null
    }
}