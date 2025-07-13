package com.yuta0124.wantedlyapp.feature.favorite

sealed interface Intent {
    data class BookmarkClick(val id: Int) : Intent
} 