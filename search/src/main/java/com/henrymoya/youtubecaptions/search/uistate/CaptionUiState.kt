package com.henrymoya.youtubecaptions.search.uistate

import com.henrymoya.youtubecaptions.searchdata.model.CaptionVideoResult


data class CaptionUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isErrorConnection: Boolean = false,
    val captionsResult: List<CaptionVideoResult> = emptyList()
) {
    companion object {
        val Empty = CaptionUiState(isLoading = false)
        val Loading = CaptionUiState(isLoading = true)
    }
}
