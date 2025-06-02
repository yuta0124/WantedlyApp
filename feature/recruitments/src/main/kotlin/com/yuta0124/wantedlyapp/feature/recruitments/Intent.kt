package com.yuta0124.wantedlyapp.feature.recruitments

sealed interface Intent {
    data class KeywordChange(val newKeyword: String) : Intent
    data object Search : Intent
}
