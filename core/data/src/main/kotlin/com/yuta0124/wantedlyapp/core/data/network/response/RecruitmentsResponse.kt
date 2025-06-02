package com.yuta0124.wantedlyapp.core.data.network.response

import com.yuta0124.wantedlyapp.core.model.Recruitment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecruitmentsResponse(
    @SerialName("data")
    val data: List<Data>,
    @SerialName("_metadata")
    val metadata: Metadata?,
)

fun RecruitmentsResponse.toRecruitmentList(): List<Recruitment> = data.map {
    Recruitment(
        id = it.id,
        title = it.title,
        companyName = it.company.name,
        companyLogoImage = it.company.avatar?.original,
        thumbnailUrl = it.image.original,
    )
}
