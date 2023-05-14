package com.example.base.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowInsets
import android.view.WindowManager
import com.example.base.App
import com.example.base.model.DeviceSize

fun Int.dpToPx(): Int = toFloat().dpToPx()

fun Int.toDp() : Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    App.getInstance().resources.displayMetrics
)

fun Double.toDp() : Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    App.getInstance().resources.displayMetrics
)

fun Float.dpToPx(): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        App.getInstance().resources.displayMetrics
    ).toInt()

fun Int.spToPx(): Int = toFloat().spToPx()

fun Float.spToPx(): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        App.getInstance().resources.displayMetrics
    ).toInt()

fun Int.dpToSp(): Int = toFloat().dpToSp()

fun Float.dpToSp(): Int =
    (this.dpToPx() / App.getInstance().resources.displayMetrics.scaledDensity).toInt()

fun Int.pxToDp(): Int = toFloat().pxToDp()

fun Float.pxToDp(): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        this,
        App.getInstance().resources.displayMetrics
    ).toInt()

fun Int.getDimensionPixelSize(): Int =
    App.getInstance().resources.getDimensionPixelSize(this)

fun Context.getDeviceSize() : DeviceSize {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics =  windowManager.currentWindowMetrics
        val currentBounds = windowMetrics.bounds

        DeviceSize(currentBounds.width(), currentBounds.height())
    } else {
        val outMetrics = DisplayMetrics()
        val display = windowManager.defaultDisplay
        display.getMetrics(outMetrics)

        val size = Point()
        display.getSize(size)

        DeviceSize(size.x, size.y)
    }
}

@SuppressLint("InternalInsetResource", "DiscouragedApi")
fun Context.getStatusBarSize(): Int {
    var statusBarHeight = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) statusBarHeight = resources.getDimensionPixelSize(resourceId)

    return statusBarHeight
}
@SuppressLint("InternalInsetResource", "DiscouragedApi")
fun Context.getNavigationBarSize(): Int {
    var navigationBarHeight = 0
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    if (resourceId > 0) navigationBarHeight = resources.getDimensionPixelSize(resourceId)

    return navigationBarHeight
}

@Suppress("DEPRECATION")
// 'getDefaultDisplay()' was deprecated in API level 30.
// 'getRealMetrics()' was deprecated in API level 31.
fun Context.getScreenWidth(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        windowMetrics.bounds.width() - insets.left - insets.right
    } else {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        displayMetrics.widthPixels
    }
}

@Suppress("DEPRECATION")
// 'getDefaultDisplay()' was deprecated in API level 30.
// 'getRealMetrics()' was deprecated in API level 31.
fun Context.getScreenHeight(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        windowMetrics.bounds.height() - insets.bottom - insets.top
    } else {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        displayMetrics.heightPixels - getStatusBarSize() - getNavigationBarSize()
    }
}