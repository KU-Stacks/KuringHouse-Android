package com.yeonkyu.data.di

import com.yeonkyu.data.BuildConfig
import com.yeonkyu.data.source.remote.AuthClient
import com.yeonkyu.data.source.remote.ApiService
import com.yeonkyu.data.source.remote.RoomClient
import com.yeonkyu.data.source.remote.RoomEventListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val API_BASE_URL = "https://api-${BuildConfig.SENDBIRD_APP_ID}.calls.sendbird.com/"

    @Provides
    @Singleton
    fun provideAuthClient(): AuthClient {
        return AuthClient()
    }

    @Provides
    @Singleton
    fun provideRoomClient(apiService: ApiService): RoomClient {
        return RoomClient(apiService)
    }

    @Provides
    @Singleton
    fun provideRoomListenerImpl() : RoomEventListener {
        return RoomEventListener()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain
                    .request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json;charset=utf-8")
                    .addHeader("Api-Token", BuildConfig.SENDBIRD_API_TOKEN)
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}