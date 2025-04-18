package com.bpn.trainme.di

import android.content.Context
import androidx.room.Room
import com.bpn.trainme.data.datasource.LocalExerciseDataSource
import com.bpn.trainme.data.datasource.RemoteExerciseDataSource
import com.bpn.trainme.data.local.ExerciseDao
import com.bpn.trainme.data.local.ExerciseDatabase
import com.bpn.trainme.data.remote.ExerciseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideExerciseDatabase(@ApplicationContext context: Context): ExerciseDatabase {
        return Room.databaseBuilder(
            context,
            ExerciseDatabase::class.java,
            "exercise.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideExerciseDao(exerciseDatabase: ExerciseDatabase) = exerciseDatabase.exerciseDao()

    @Provides
    @Singleton
    fun provideLocalExerciseDataSource(exerciseDao: ExerciseDao) =
        LocalExerciseDataSource(exerciseDao)

    @Provides
    @Singleton
    fun provideRemoteExerciseDataSource(exerciseApi: ExerciseApi) =
        RemoteExerciseDataSource(exerciseApi)

}