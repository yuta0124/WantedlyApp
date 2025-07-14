package com.yuta0124.wantedlyapp.core.model

data class RecruitmentDetail(
    val id: Int = -1,
    val title: String = "",
    val companyName: String = "",
    val thumbnailUrl: String = "",
    val canBookmark: Boolean = false,
    val companyLogoImage: String? = null,
    val whatDescription: String? = null,
    val whyDescription: String? = null,
    val howDescription: String? = null,
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
