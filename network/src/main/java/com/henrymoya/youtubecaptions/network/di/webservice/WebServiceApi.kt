package com.henrymoya.youtubecaptions.network.di.webservice

import com.henrymoya.youtubecaptions.network.di.model.search.CaptionVideoRequest
import com.henrymoya.youtubecaptions.network.di.model.search.CaptionVideoResponse
import com.henrymoya.youtubecaptions.network.di.model.search.NextVideoRequest
import com.henrymoya.youtubecaptions.network.di.model.search.SearchResponse
import retrofit2.Response
import retrofit2.http.*

interface WebServiceApi {
    companion object {
        const val API_VERSION = "v1/"
    }

    // método para resibir un listado de texto

    @GET("${API_VERSION}videos/search")
    suspend fun search(
        @Query("text") text: String,
        @Query("lan") language: String,
        @Query("ac") accent: String,
    ): Response<SearchResponse>


    // metodo para ingresar un texto y resibir un listado de subtitulo

    @POST("${API_VERSION}videos/next")
    suspend fun nextVideo(
        @Body data: NextVideoRequest
    ): Response<SearchResponse>


    // método para obtener subtitulo de un video
    @POST("${API_VERSION}videos/getCaption")
    suspend fun getCaptions(@Body data: CaptionVideoRequest): Response<List<CaptionVideoResponse>>


}