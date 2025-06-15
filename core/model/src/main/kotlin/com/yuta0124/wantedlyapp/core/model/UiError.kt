package com.yuta0124.wantedlyapp.core.model

sealed interface UiError {
    data object BadRequestError : UiError
    data object ServerError : UiError
    data object TimeoutError : UiError
    data object UnexpectedError : UiError
    data object NetworkError : UiError
}
