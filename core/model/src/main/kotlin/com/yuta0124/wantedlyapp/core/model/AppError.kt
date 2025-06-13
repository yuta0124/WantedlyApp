package com.yuta0124.wantedlyapp.core.model

sealed interface AppError {
    /** status code: 400~499 */
    data object BadRequestException : AppError
    /** status code: 500~599 */
    data object ServerException : AppError
    data object NetworkException : AppError
    data object TimeoutException : AppError
    data object ExpectedException : AppError
}
