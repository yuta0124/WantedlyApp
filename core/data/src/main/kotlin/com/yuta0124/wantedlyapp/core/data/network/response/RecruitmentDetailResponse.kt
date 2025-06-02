package com.yuta0124.wantedlyapp.core.data.network.response

import com.yuta0124.wantedlyapp.core.model.RecruitmentDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecruitmentDetailResponse(
    @SerialName("data")
    val data: DetailData,
)

fun RecruitmentDetailResponse.toRecruitmentDetail(): RecruitmentDetail = RecruitmentDetail(
    id = data.id,
    title = data.title,
    thumbnailUrl = data.image.original,
    whatDescription = data.whatDescription,
    whyDescription = data.whyDescription,
    howDescription = data.whoDescription,
    companyName = data.company.name,
    companyLogoImage = data.company.avatar?.original,
)
