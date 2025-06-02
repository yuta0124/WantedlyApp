package com.yuta0124.wantedlyapp.feature.recruitments

import com.yuta0124.wantedlyapp.core.model.Recruitment

data class UiState(
    val loading: Loading = Loading.NONE,
    val isPageLimit: Boolean = false,
    val recruitments: List<Recruitment> = emptyList(),
    val keyword: String? = null,
) {
    enum class Loading {
        NONE,
        INDICATOR,
        ADDITIONAL,
    }
}
