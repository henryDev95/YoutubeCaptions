package com.henrymoya.youtubecaptions.network.exception

import com.henrymoya.youtubecaptions.network.di.model.error.ErrorModel

class CustomException(
    val error: ErrorModel
): Throwable()