package com.example.base.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onTextChanged(
    onChanged: (String) -> Unit = {},
    onBeforeChanged: (String) -> Unit = {},
    onAfterChanged: (String) -> Unit = {}
) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            s?.let { onBeforeChanged(s.toString()) }
        }
        override fun afterTextChanged(s: Editable?) {
            s?.let { onAfterChanged(s.toString()) }
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let { onChanged(s.toString()) }
        }
    })
}