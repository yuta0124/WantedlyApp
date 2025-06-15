package com.yuta0124.wantedlyapp.core.data

import com.yuta0124.wantedlyapp.core.model.AppError
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.util.cio.ChannelReadException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.io.IOException

@Suppress("MagicNumber")
fun Throwable.toAppError(): AppError = when (this) {
    is ResponseException -> when (this.response.status.value) {
        in 400..499 -> AppError.BadRequestException
        in 500..599 -> AppError.ServerException
        else -> AppError.UnexpectedException
    }

    is TimeoutCancellationException,
    is HttpRequestTimeoutException,
    is SocketTimeoutException -> AppError.TimeoutException

    is ChannelReadException,
    is IOException -> AppError.NetworkException

    else -> AppError.UnexpectedException
}
