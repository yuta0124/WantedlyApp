package com.yuta0124.wantedlyapp.feature.recruitments

import com.yuta0124.wantedlyapp.core.model.UiError

sealed interface UiEvent {
    data class ShowErrorMessage(val uiError: UiError) : UiEvent
}
