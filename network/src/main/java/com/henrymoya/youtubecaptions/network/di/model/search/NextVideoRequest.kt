package com.henrymoya.youtubecaptions.network.di.model.search

import com.google.gson.annotations.SerializedName

data class NextVideoRequest(
    @SerializedName("nextPageToken") val nextPageToken: String,
)