package com.yuta0124.wantedlyapp.feature.recruitments

import com.yuta0124.wantedlyapp.core.model.Recruitment

data class UiState(
    val keyword: String = "",
    val isLoading: Boolean = true,
    val recruitments: List<Recruitment> = emptyList(),
)
