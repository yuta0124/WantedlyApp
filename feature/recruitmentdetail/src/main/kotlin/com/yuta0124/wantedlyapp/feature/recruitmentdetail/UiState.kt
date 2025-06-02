package com.yuta0124.wantedlyapp.feature.recruitmentdetail

import com.yuta0124.wantedlyapp.core.model.RecruitmentDetail

data class UiState(
    val isLoading: Boolean = true,
    val recruitmentDetail: RecruitmentDetail = RecruitmentDetail(),
)
