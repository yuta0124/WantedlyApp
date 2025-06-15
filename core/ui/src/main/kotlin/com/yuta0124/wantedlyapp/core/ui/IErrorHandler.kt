package com.yuta0124.wantedlyapp.core.ui

import com.yuta0124.wantedlyapp.core.model.AppError
import com.yuta0124.wantedlyapp.core.ui.UiError.BadRequestError
import com.yuta0124.wantedlyapp.core.ui.UiError.NetworkError
import com.yuta0124.wantedlyapp.core.ui.UiError.ServerError
import com.yuta0124.wantedlyapp.core.ui.UiError.TimeoutError
import com.yuta0124.wantedlyapp.core.ui.UiError.UnexpectedError
import javax.inject.Inject

fun interface IErrorHandler {
    fun onError(error: AppError): UiError
}

class ErrorHandler @Inject constructor() : IErrorHandler {
    override fun onError(error: AppError): UiError = when (error) {
        is AppError.BadRequestException -> BadRequestError()
        is AppError.ServerException -> ServerError()
        AppError.NetworkException -> NetworkError()
        AppError.TimeoutException -> TimeoutError()
        AppError.UnexpectedException -> UnexpectedError()
    }
}
