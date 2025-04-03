package com.gsanchez.kmpsalesmobil

actual class GetDeviceInformation {
    actual fun getDeviceInfo(): String {
        return "Java ${System.getProperty("java.version")}"
    }
}