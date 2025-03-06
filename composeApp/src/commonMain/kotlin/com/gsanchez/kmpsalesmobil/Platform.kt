package com.gsanchez.kmpsalesmobil

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform