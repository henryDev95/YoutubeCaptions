package com.henrymoya.youtubecaptions.domain.search.mapper

import com.henrymoya.youtubecaptions.network.di.model.search.CaptionVideoResponse
import com.henrymoya.youtubecaptions.searchdata.model.CaptionVideoResult

internal fun CaptionVideoResponse.toCaptionVideoResult() =
    CaptionVideoResult(
       start = start,
        dur = dur,
        text = text
    )