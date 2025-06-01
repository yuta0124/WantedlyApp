package com.yuta0124.wantedlyapp.core.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Metadata(
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total_objects")
    val totalObjects: Int,
    @SerialName("total_pages")
    val totalPages: Int,
)
