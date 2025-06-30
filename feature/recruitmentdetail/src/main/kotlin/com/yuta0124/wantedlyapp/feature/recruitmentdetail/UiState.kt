package com.yuta0124.wantedlyapp.feature.recruitmentdetail

import com.yuta0124.wantedlyapp.core.model.RecruitmentDetail

data class UiState(
    val loading: Loading = Loading.INDICATOR,
    val recruitmentDetail: RecruitmentDetail = RecruitmentDetail(),
) {
    enum class Loading {
        NONE,
        INDICATOR,
        ERROR,
    }
}
