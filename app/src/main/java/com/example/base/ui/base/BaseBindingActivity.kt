package com.example.base.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.example.base.extensions.binding

abstract class BaseBindingActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
) : AppCompatActivity() {

    private var _binding: T? = null

    protected val binding: T
        get() = checkNotNull(_binding) {
            "Activity $this binding cannot be accessed before onCreate() or after onDestroy()"
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = binding(layoutResId)
    }
    override fun onDestroy() {
        _binding?.unbind()
        _binding = null
        super.onDestroy()
    }
}