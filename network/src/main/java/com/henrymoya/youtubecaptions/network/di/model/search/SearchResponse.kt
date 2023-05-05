package com.henrymoya.youtubecaptions.network.di.model.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items") val items: List<ItemResponse> = emptyList(),
    @SerializedName("total") val total: Int,
    @SerializedName("nextPage") val nextPage: NextVideoResponse,
)

data class ItemResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("start") val start: String,
)