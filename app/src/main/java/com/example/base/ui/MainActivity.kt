package com.example.base.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.example.base.MainViewModel
import com.example.base.R
import com.example.base.databinding.ActivityMainBinding
import com.example.base.extensions.startActivity
import com.example.base.ui.base.BaseBindingActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>(
    layoutResId = R.layout.activity_main
) {
    private val viewModel by viewModels<MainViewModel>()
    lateinit var overlayView: View
    private var dx = 0
    private var dy = 0
    private val moveX = 0f
    private val moveY = 0f

    @RequiresApi(Build.VERSION_CODES.O)
    private val overlayActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (Settings.canDrawOverlays(this)) {
            showOverlayView()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("QueryPermissionsNeeded", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overlayView =  LayoutInflater.from(this).inflate(R.layout.overlay_view, null) // 추가할 뷰

        binding.btnSendEmail.setOnClickListener {
//            val emailSelectorIntent = Intent(Intent.ACTION_SENDTO)
//            emailSelectorIntent.data = Uri.parse("mailto:")
//
//            val intent = Intent(Intent.ACTION_SEND)
//            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("bravery712@naver.com")) // 받는 사람 이메일
//            intent.putExtra(Intent.EXTRA_SUBJECT, "Email Title") // 메일 제목
//            intent.putExtra(Intent.EXTRA_TEXT, "Email TextEmail TextEmail TextEmail TextEmail TextEmail TextEmail TextEmail TextEmail TextEmail TextEmail Text") // 메일 내용
////            intent.putExtra(Intent.EXTRA_STREAM, fileUri)
//            intent.selector = emailSelectorIntent
//
//            if(intent.resolveActivity(packageManager) != null) {
//                startActivity(intent)
//            }

            reqPermission()

            overlayView.setOnTouchListener { v, event ->
                val layoutParams = overlayView.layoutParams as WindowManager.LayoutParams

                when(event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dx = (layoutParams.x - event.rawX).toInt()
                        dy = (layoutParams.y - event.rawY).toInt()
                    }
                    MotionEvent.ACTION_MOVE -> {
                        layoutParams.x = (dx + event.rawX).toInt()
                        layoutParams.y = (dy + event.rawY).toInt()

                        windowManager.updateViewLayout(overlayView, layoutParams)
                    }
                    MotionEvent.ACTION_UP -> {

                    }
                }

                false
            }

            overlayView.setOnClickListener {
                startActivity(MainActivity::class.java) {
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun reqPermission() {
        if (Settings.canDrawOverlays(this)) {
            showOverlayView()
        } else {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                "package:$packageName".toUri()
            )

            overlayActivityResult.launch(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showOverlayView() {
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,  // 다른 앱 위에 그리기 권한이 필요한 유형
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )


        windowManager.addView(overlayView, params) // 뷰 추가
    }

    private fun hideOverlayView() {
        windowManager.removeView(overlayView)
    }
}