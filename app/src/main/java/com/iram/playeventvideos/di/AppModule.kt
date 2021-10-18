package com.iram.playeventvideos.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iram.playeventvideos.BuildConfig
import com.iram.playeventvideos.network.ServerDataSource
import com.iram.playeventvideos.network.iService
import com.iram.playeventvideos.repository.EventScheduleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(350, TimeUnit.SECONDS)
            .readTimeout(350, TimeUnit.SECONDS)
            .writeTimeout(350, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.EVENTS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideService(retrofit: Retrofit): iService = retrofit.create(iService::class.java)


    @Singleton
    @Provides
    fun provideRepository(serverDataSource: ServerDataSource) = EventScheduleRepository(serverDataSource)
}