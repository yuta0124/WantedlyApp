package com.yuta0124.wantedlyapp.core.ui

import androidx.annotation.StringRes
import com.yuta0124.wantedlyapp.core.design.system.R

sealed class UiError(@StringRes val messageRes: Int) {
    data class BadRequestError(@StringRes val res: Int = R.string.bad_request_error_message) :
        UiError(res)

    data class ServerError(@StringRes val res: Int = R.string.server_error_message) : UiError(res)
    data class TimeoutError(@StringRes val res: Int = R.string.timeout_error_message) : UiError(res)
    data class UnexpectedError(@StringRes val res: Int = R.string.unexpected_error_message) :
        UiError(res)

    data class NetworkError(@StringRes val res: Int = R.string.network_error_message) : UiError(res)
}
