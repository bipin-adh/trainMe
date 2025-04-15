package com.bpn.trainme.di

import com.bpn.trainme.data.remote.ExerciseApi
import com.bpn.trainme.data.repository.ExerciseRepositoryImpl
import com.bpn.trainme.domain.repository.ExerciseRepository
import com.bpn.trainme.presentation.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesExerciseRepository(api: ExerciseApi): ExerciseRepository {
        return ExerciseRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideNavigationManager(): NavigationManager{
        return NavigationManager()
    }
}