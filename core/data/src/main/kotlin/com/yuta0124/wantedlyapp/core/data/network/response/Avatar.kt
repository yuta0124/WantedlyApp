package com.yuta0124.wantedlyapp.core.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Avatar(
    @SerialName("original")
    val original: String?,
)
