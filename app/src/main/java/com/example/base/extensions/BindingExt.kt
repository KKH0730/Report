package com.example.base.extensions

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


/**
 * activity binding
 */
fun <T : ViewDataBinding> Activity.binding(
    @LayoutRes layoutResId: Int,
): T = DataBindingUtil.setContentView(this, layoutResId)

/**
 * fragment binding
 */
fun <T : ViewDataBinding> Fragment.binding(
    @LayoutRes layoutResId: Int,
    inflater: LayoutInflater,
    viewGroup: ViewGroup?
): T = DataBindingUtil.inflate(
    inflater,
    layoutResId,
    viewGroup,
    false
)

/**
 * viewGroup binding (viewHolder)
 */
fun <T : ViewDataBinding> ViewGroup.binding(
    @LayoutRes layoutResId: Int
): T = DataBindingUtil.inflate(
    context.layoutInflater,
    layoutResId,
    this,
    false,
)
