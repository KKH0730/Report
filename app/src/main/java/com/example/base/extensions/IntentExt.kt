package com.example.base.extensions

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

fun Context.startActivity(action: String, builder: (Intent.() -> Unit)) {
    startActivity(Intent(action).apply(builder))
}

fun <T> Context.startActivity(
    activityClass: Class<T>,
    builder: (Intent.() -> Unit),
) {
    startActivity(Intent(this, activityClass).apply(builder))
}

fun <T> Context.startActivity(
    activityClass: Class<T>,
) {
    startActivity(Intent(this, activityClass))
}

fun <T> Context.startActivity(
    activityClass: Class<T>,
    launcher: ActivityResultLauncher<Intent?>,
    builder: (Intent.() -> Unit),
) {
    launcher.launch(Intent(this, activityClass).apply(builder))
}

fun <T> Context.startActivity(
    activityClass: Class<T>,
    launcher: ActivityResultLauncher<Intent?>,
) {
    launcher.launch(Intent(this, activityClass))
}

fun Context.safetyStartActivity(
    intent: Intent,
    catch: ((e: Exception) -> Unit)? = null,
) {
    try {
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
        catch?.let {
            it(e)
        }
    }
}