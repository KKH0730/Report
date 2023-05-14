package com.example.base.extensions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

fun checkPermissions(
    permissions: Array<String>,
    listener: PermissionListener
) {
    TedPermission.create()
        .setPermissions(*permissions)
        .setPermissionListener(listener)
        .check()
}

fun checkPermissions(
    permissions: Array<String>,
    deniedMessage: String,
    listener: PermissionListener
) {
    TedPermission.create()
        .setPermissions(*permissions)
        .setDeniedMessage(deniedMessage)
        .setPermissionListener(listener)
        .check()
}

fun Context.hasCameraPermission() : Boolean =
    ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED