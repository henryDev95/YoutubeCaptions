package com.henrymoya.youtubecaptions.network.di.model.search

import com.google.gson.annotations.SerializedName

data class CaptionVideoResponse(
  @SerializedName("start") val  start:String,
  @SerializedName("dur") val dur:String,
  @SerializedName("text") val text:String
)
