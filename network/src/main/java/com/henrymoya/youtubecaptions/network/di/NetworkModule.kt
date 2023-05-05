package com.henrymoya.youtubecaptions.network.di

import android.content.Context
import com.henrymoya.youtubecaptions.network.BuildConfig
import com.henrymoya.youtubecaptions.network.BuildConfig.API_HOST
import com.henrymoya.youtubecaptions.network.BuildConfig.API_KEY
import com.henrymoya.youtubecaptions.network.di.webservice.WebServiceApi
import com.loogika.ysearch.network.di.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val URL = "$API_HOST/api/"
    @Provides
    fun provideOkHttpClient(@ApplicationContext appContext: Context
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        okHttpClientBuilder.addInterceptor(AuthInterceptor(API_KEY))
        okHttpClientBuilder.connectTimeout(5000, TimeUnit.MILLISECONDS)
        okHttpClientBuilder.readTimeout(5000,TimeUnit.MILLISECONDS)

        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext appContext: Context, okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

   @Singleton
    @Provides
    fun provideMovieApiClient(retrofit: Retrofit): WebServiceApi {
        return retrofit.create(WebServiceApi::class.java)
    }
}