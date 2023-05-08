package com.henrymoya.youtubecaptions.network.di.model.search

import com.google.gson.annotations.SerializedName

data class CaptionVideoRequest(
    @SerializedName("videoId") val videoId:String
)
