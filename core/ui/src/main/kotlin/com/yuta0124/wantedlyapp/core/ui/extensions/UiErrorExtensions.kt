package com.yuta0124.wantedlyapp.core.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.yuta0124.wantedlyapp.core.design.system.R
import com.yuta0124.wantedlyapp.core.model.UiError

@Composable
fun UiError.toMessage(): String {
    return when (this) {
        UiError.BadRequestError -> stringResource(R.string.bad_request_error_message)
        UiError.UnexpectedError -> stringResource(R.string.unexpected_error_message)
        UiError.NetworkError -> stringResource(R.string.network_error_message)
        UiError.ServerError -> stringResource(R.string.server_error_message)
        UiError.TimeoutError -> stringResource(R.string.timeout_error_message)
    }
}
