package com.henrymoya.youtubecaptions.searchdata.datasource

import com.henrymoya.youtubecaptions.core.strings.getYoutubeVideoId
import com.henrymoya.youtubecaptions.network.di.DefaultDispatcher
import com.henrymoya.youtubecaptions.network.di.model.error.ErrorModel
import com.henrymoya.youtubecaptions.network.di.model.error.ErrorModel.Companion.StatusCode401
import com.henrymoya.youtubecaptions.network.di.model.error.ErrorModel.Companion.StatusCode422
import com.henrymoya.youtubecaptions.network.di.model.error.ErrorModel.Companion.StatusCode500
import com.henrymoya.youtubecaptions.network.di.model.search.CaptionVideoRequest
import com.henrymoya.youtubecaptions.network.di.model.search.CaptionVideoResponse
import com.henrymoya.youtubecaptions.network.di.util.ErrorType
import com.henrymoya.youtubecaptions.network.di.util.ResultType
import com.henrymoya.youtubecaptions.network.di.util.toErrorMessage
import com.henrymoya.youtubecaptions.network.di.webservice.WebServiceApi
import com.henrymoya.youtubecaptions.searchdata.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    private val api: WebServiceApi,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : SearchRepository {
    override suspend fun search(url: String): Flow<ResultType<List<CaptionVideoResponse>?, ErrorType>> {
        val loanApplicationsResult = kotlin.runCatching {
            val queryResult = api.getCaptions(CaptionVideoRequest(url.getYoutubeVideoId()))
            queryResult
        }

        return flow {
            loanApplicationsResult.onSuccess { data ->
                if (data.isSuccessful) {
                    emit(
                        ResultType.Success(
                            data.body()
                        )
                    )
                } else {
                    when (data.code()) {
                        StatusCode401 -> emit(
                            ResultType.Error(
                                ErrorType.NetworkAuthHttpException
                            )
                        )
                        StatusCode422 -> emit(
                            ResultType.Error(
                                ErrorType.Error(
                                    ErrorModel(
                                        code = data.code().toString(),
                                        message = data.errorBody()?.toErrorMessage("") ?: ""
                                    )
                                )
                            )
                        )
                        StatusCode500 -> emit(
                            ResultType.Error(
                                ErrorType.NetworkConnectionHttpException
                            )
                        )
                        else -> emit(
                            ResultType.Error(
                                ErrorType.Error(
                                    ErrorModel(
                                        code = data.code().toString(),
                                        message = data.message()
                                    )
                                )
                            )
                        )
                    }
                }

            }.onFailure { ex ->
                if (ex is SocketTimeoutException) {
                    emit(
                        ResultType.Error(
                            ErrorType.NetworkConnectionHttpException
                        )
                    )
                }

                if (ex is HttpException) {
                    emit(
                        ResultType.Error(
                            ErrorType.NetworkHttpExceptionWithCode(
                                retrofitHttpException = ex
                            )
                        )
                    )
                }
                emit(ResultType.Error(ErrorType.Exception(ex)))
            }

        }.flowOn(dispatcher)
    }
}