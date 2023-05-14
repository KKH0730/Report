package com.example.base.extensions

import android.annotation.SuppressLint
import android.view.Window
import android.view.WindowManager


@SuppressLint("ResourceAsColor")
fun Window.setStatusColor(color: Int) {
    this.apply {
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = color
    }
}