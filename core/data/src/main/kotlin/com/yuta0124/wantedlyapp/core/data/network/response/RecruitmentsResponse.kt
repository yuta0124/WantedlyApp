package com.yuta0124.wantedlyapp.core.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecruitmentsResponse(
    @SerialName("data")
    val data: List<Data>,
    @SerialName("_metadata")
    val metadata: Metadata,
)