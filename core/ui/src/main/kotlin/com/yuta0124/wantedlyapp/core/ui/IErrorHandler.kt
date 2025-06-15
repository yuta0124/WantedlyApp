package com.yuta0124.wantedlyapp.core.ui

import com.yuta0124.wantedlyapp.core.model.AppError
import com.yuta0124.wantedlyapp.core.model.UiError
import javax.inject.Inject

fun interface IErrorHandler {
    fun onError(error: AppError): UiError
}

class ErrorHandler @Inject constructor() : IErrorHandler {
    override fun onError(error: AppError): UiError = when (error) {
        AppError.BadRequestException -> UiError.BadRequestError
        AppError.ServerException -> UiError.ServerError
        AppError.NetworkException -> UiError.NetworkError
        AppError.TimeoutException -> UiError.TimeoutError
        AppError.UnexpectedException -> UiError.UnexpectedError
    }
}
