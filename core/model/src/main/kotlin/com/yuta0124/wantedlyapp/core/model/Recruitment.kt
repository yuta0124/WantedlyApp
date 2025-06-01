package com.yuta0124.wantedlyapp.core.model

data class Recruitment(
    val id: Int,
    val title: String,
    val companyName: String,
    val companyLogoImage: String,
    val thumbnailUrl: String,
) {
    companion object {
        fun fake(): List<Recruitment> = (0..10).map {
            Recruitment(
                id = it,
                title = "サンプルタイトル文",
                companyName = "サンプル企業名",
                companyLogoImage = "",
                thumbnailUrl = "",
            )
        }
    }
}
