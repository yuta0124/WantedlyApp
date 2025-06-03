package com.yuta0124.wantedlyapp.core.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailData(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("image")
    val image: Image,
    @SerialName("company")
    val company: Company,
    @SerialName("what_description")
    val whatDescription: String,
    @SerialName("why_description")
    val whyDescription: String,
    @SerialName("how_description")
    val whoDescription: String,
)
