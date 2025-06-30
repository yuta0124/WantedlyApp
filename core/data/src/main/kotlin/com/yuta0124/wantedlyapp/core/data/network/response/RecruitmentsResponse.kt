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
    it.run {
        Recruitment(
            id = id,
            title = title,
            companyName = company.name,
            canBookMark = canBookmark,
            companyLogoImage = company.avatar?.original,
            thumbnailUrl = image.original,
        )
    }
}
