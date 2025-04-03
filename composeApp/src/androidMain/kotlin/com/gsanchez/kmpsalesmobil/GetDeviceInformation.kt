package com.gsanchez.kmpsalesmobil

import android.os.Build

actual class GetDeviceInformation {
    actual fun getDeviceInfo(): String {
        return "Android ${Build.VERSION.SDK_INT}"
    }
}