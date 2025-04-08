package com.bpn.trainme.di

import com.bpn.trainme.BuildConfig
import com.bpn.trainme.network.ApiService
import com.bpn.trainme.repository.Repository
import com.bpn.trainme.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesRetrofit(): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(
            Json.asConverterFactory(
                "application/json; charset=UTF8".toMediaType()))
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesRepository(repositoryImpl: RepositoryImpl): Repository = repositoryImpl
}