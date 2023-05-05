package com.henrymoya.youtubecaptions.searchdata.repository

import com.henrymoya.youtubecaptions.network.di.model.search.CaptionVideoResponse
import com.henrymoya.youtubecaptions.network.di.util.ErrorType
import com.henrymoya.youtubecaptions.network.di.util.ResultType
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun search(url:String)
            : Flow<ResultType<List<CaptionVideoResponse>?, ErrorType>>

}