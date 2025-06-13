package com.yuta0124.wantedlyapp.feature.recruitmentdetail

sealed interface Intent {
    data class BookmarkClick(val canBookmark: Boolean) : Intent
}