package com.example.base.extensions

import android.content.Context
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.example.base.App

//Extensions
fun Int.asColor() = ContextCompat.getColor(App.getInstance(), this)
fun Int.asDrawable() = ContextCompat.getDrawable(App.getInstance(), this)

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)