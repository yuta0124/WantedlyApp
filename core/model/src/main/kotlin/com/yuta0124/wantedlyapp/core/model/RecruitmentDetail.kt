package com.yuta0124.wantedlyapp.core.model

data class RecruitmentDetail(
    val thumbnailUrl: String = "",
    val title: String? = null,
    val whatDescription: String? = null,
    val whyDescription: String? = null,
    val howDescription: String? = null,
    val canBookmark: Boolean = false,
    val id: Int? = null,
    val companyName: String? = null,
    val companyLogoImage: String? = null,
) {
    companion object {
        fun fake(): RecruitmentDetail = RecruitmentDetail(
            id = 0,
            title = "サンプルタイトル文",
            companyName = "サンプル企業名",
            thumbnailUrl = "",
            companyLogoImage = "",
            whatDescription = "なにをしているかの説明文",
            whyDescription = "なぜそれをしているかの説明文",
            howDescription = "どのようにしているかの説明文",
        )
    }
}
