package com.gsanchez.kmpsalesmobil

import platform.UIKit.UIDevice

actual class GetDeviceInformation {
    actual fun getDeviceInfo(): String {
        return UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    }

}