package com.yuta0124.wantedlyapp.feature.recruitments

import com.yuta0124.wantedlyapp.core.model.Recruitment

data class UiState(
    val isLoading: Boolean = true,
    val isAdditionalLoading: Boolean = false,
    val recruitments: List<Recruitment> = emptyList(),
    val keyword: String? = null,
)
