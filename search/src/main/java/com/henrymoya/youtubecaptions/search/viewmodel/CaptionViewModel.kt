package com.henrymoya.youtubecaptions.search.viewmodel

import androidx.lifecycle.viewModelScope
import com.henrymoya.youtubecaptions.domain.search.GetCaptionsUseCase
import com.henrymoya.youtubecaptions.domain.viewstate.extension.*
import com.henrymoya.youtubecaptions.search.uistate.CaptionUiState
import com.henrymoya.youtubecaptions.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaptionViewModel @Inject constructor(
    private val getCaptionsUseCase: GetCaptionsUseCase,
) : BaseViewModel() {

    var captionState = MutableStateFlow(CaptionUiState.Empty)
        private set

    fun search(params: String) {
        viewModelScope.launch {
            captionState.value = CaptionUiState.Loading
            getCaptionsUseCase(params) collectAsSuccess {
                captionState.value = CaptionUiState(
                    isLoading = false, captionsResult = it ?: emptyList()
                )

            } collectAsUnAuthorized {

            } collectAsFailureErrorModel {
                captionState.value = CaptionUiState(
                    isLoading = false
                )
            } collectAsFailure {
                captionState.value = CaptionUiState(
                    isLoading = false
                )
            } collectAsErrorConnection {
                captionState.value = CaptionUiState(
                    isLoading = false
                )
            }
        }
    }
}