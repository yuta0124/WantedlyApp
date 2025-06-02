package com.yuta0124.wantedlyapp.core.model

data class RecruitmentDetail(
    val title: String = "",
    val thumbnailUrl: String = "",
    val whatDescription: String = "",
    val whyDescription: String = "",
    val howDescription: String = "",
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