package com.henrymoya.youtubecaptions.domain.search

import com.henrymoya.youtubecaptions.domain.search.mapper.toCaptionVideoResult
import com.henrymoya.youtubecaptions.domain.usecase.FlowUseCase
import com.henrymoya.youtubecaptions.domain.viewstate.UIState
import com.henrymoya.youtubecaptions.network.di.util.ErrorType
import com.henrymoya.youtubecaptions.network.di.util.ResultType
import com.henrymoya.youtubecaptions.searchdata.datasource.SearchDataSource
import com.henrymoya.youtubecaptions.searchdata.model.CaptionVideoResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCaptionsUseCase @Inject constructor(
    private val searchDataSource: SearchDataSource,
) : FlowUseCase<String, List<CaptionVideoResult>?>() {
    override suspend fun execute(parameters: String)
            : Flow<UIState<List<CaptionVideoResult>?>> =
        searchDataSource.search(parameters)
            .map { result ->
                if (result is ResultType.Success) {
                    return@map UIState.Success(
                        result.value?.map { it.toCaptionVideoResult() }
                    )
                }

                if (result is ResultType.Error) {
                    val error = result.value
                    if (error is ErrorType.Error) {
                        return@map UIState.FailureErrorModel(error.errorModel)
                    }
                    if (error is ErrorType.Exception) {
                        return@map UIState.Failure(error)
                    }
                    if (error is ErrorType.NetworkConnectionHttpException) {
                        return@map UIState.ConnectionError
                    }
                    if (error is ErrorType.NetworkAuthHttpException) {
                        return@map UIState.UnAuthorized
                    }
                }

                UIState.UnExpectedError
            }
}


