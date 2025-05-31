package com.yuta0124.wantedlyapp.core.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecruitmentDetailResponse(
    @SerialName("data")
    val data: Data,
)