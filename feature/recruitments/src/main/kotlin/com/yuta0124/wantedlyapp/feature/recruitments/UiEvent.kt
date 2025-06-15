package com.yuta0124.wantedlyapp.feature.recruitments

import com.yuta0124.wantedlyapp.core.ui.UiError

sealed interface UiEvent {
    data class ShowErrorMessage(val uiError: UiError) : UiEvent
}
