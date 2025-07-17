package com.yuta0124.wantedlyapp.feature.favorite

import com.yuta0124.wantedlyapp.core.model.Recruitment

data class UiState(
    val loading: Loading = Loading.NONE,
    val recruitments: List<Recruitment> = emptyList(),
) {
    enum class Loading {
        NONE,
        EMPTY,
    }
}
