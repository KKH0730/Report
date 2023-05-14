package com.example.base.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

inline fun <C, R> C?.ifNullOrEmpty(defaultValue: () -> R): R where R : CharSequence, C : R =
    if (isNullOrEmpty()) defaultValue() else this

@OptIn(ExperimentalContracts::class)
fun CharSequence?.isNotNullAndNotEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullAndNotEmpty != null)
    }

    return !isNullOrEmpty()
}

fun String.fromHtml(): Spanned{
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        Html.fromHtml(this)
    } else {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    }
}
