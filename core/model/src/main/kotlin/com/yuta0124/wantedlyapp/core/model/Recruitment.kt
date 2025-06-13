package com.yuta0124.wantedlyapp.core.model

data class Recruitment(
    val id: Int,
    val title: String,
    val companyName: String,
    val canBookMark: Boolean,
    val companyLogoImage: String?,
    val thumbnailUrl: String,
) {
    companion object {
        @Suppress("MagicNumber")
        fun fake(): List<Recruitment> = (0..10).map {
            Recruitment(
                id = it,
                title = "サンプルタイトル文",
                canBookMark = true,
                companyName = "サンプル企業名",
                companyLogoImage = "",
                thumbnailUrl = "",
            )
        }
    }
}
