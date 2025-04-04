package com.gsanchez.kmpsalesmobil.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerialName("response")
    val ok: String,
    val results: List<Hero>
)
