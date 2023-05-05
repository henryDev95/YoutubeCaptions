package com.henrymoya.youtubecaptions.network.di.util

import com.henrymoya.youtubecaptions.network.di.model.error.ErrorModel
import retrofit2.HttpException


sealed class ErrorType {
    data class Error(
        val errorModel: ErrorModel
    ) : ErrorType()

    data class Exception(
        val throwable: Throwable
    ) : ErrorType()

    data class NetworkException(
        val retrofitNetworkException: HttpException
    ) : ErrorType()

    data class NetworkHttpExceptionWithCode(
        val retrofitHttpException: HttpException
    ) : ErrorType()

    object NetworkAuthHttpException : ErrorType()

    object NetworkConnectionHttpException : ErrorType()
}