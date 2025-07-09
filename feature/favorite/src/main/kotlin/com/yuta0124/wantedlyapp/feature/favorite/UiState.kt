package com.yuta0124.wantedlyapp.feature.favorite

data class UiState(
    val loading: Loading = Loading.INDICATOR,
) {
    enum class Loading {
        NONE,
        INDICATOR,
    }
}
