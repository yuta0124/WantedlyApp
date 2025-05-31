package com.yuta0124.wantedlyapp.core.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Leader(
    @SerialName("facebook_uid")
    val facebookUid: String?,
    @SerialName("name_en")
    val nameEn: String?,
    @SerialName("name_ja")
    val nameJa: String?,
)