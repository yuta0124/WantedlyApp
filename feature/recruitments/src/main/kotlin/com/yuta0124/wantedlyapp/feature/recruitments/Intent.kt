package com.yuta0124.wantedlyapp.feature.recruitments

sealed interface Intent {
    data class KeywordChange(val newKeyword: String) : Intent
    data class BookmarkClick(val id: Int, val canBookmark: Boolean) : Intent
    data object Search : Intent
    data object AdditionalRecruitments : Intent
}
