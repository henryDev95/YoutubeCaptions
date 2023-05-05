package com.loogika.ysearch.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrymoya.youtubecaptions.core.navegation.compose.NavigatorEffectFlowParams
import com.henrymoya.youtubecaptions.core.navegation.compose.NavigatorParams
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val _effect: Channel<NavigatorParams> = Channel()
    val effect = _effect.receiveAsFlow()

    protected fun setEffect(builder: () -> NavigatorParams) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    private val _effectFlow: Channel<NavigatorEffectFlowParams> = Channel()
    val effectFlow = _effectFlow.receiveAsFlow()

    protected fun setEffectFlow(builder: () -> NavigatorEffectFlowParams) {
        viewModelScope.launch { _effectFlow.send(builder()) }
    }
}