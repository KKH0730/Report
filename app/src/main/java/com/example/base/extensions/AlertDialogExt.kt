package com.example.base.extensions

import android.app.Activity
import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.databinding.ViewDataBinding

fun <T: ViewDataBinding> T.showAlertDialog(activity: Activity) : AlertDialog {
    val builder = AlertDialog.Builder(activity).apply {
        setView(this@showAlertDialog.root)
    }
    val dialog = builder.create()
    dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    return dialog
}