package com.henrymoya.youtubecaptions.network.di.model.search

import com.google.gson.annotations.SerializedName

data class NextVideoResponse(
    @SerializedName("nextPageToken") val nextPageToken: String,
)