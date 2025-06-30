package com.yuta0124.wantedlyapp.feature.recruitmentdetail

import com.yuta0124.wantedlyapp.core.ui.UiError

sealed interface UiEvent {
    data class ShowErrorMessage(val uiError: UiError) : UiEvent
}
