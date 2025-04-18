package com.bpn.trainme.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bpn.trainme.data.datasource.LocalExerciseDataSource
import com.bpn.trainme.data.datasource.MediatorPagingSource
import com.bpn.trainme.data.datasource.RemoteExerciseDataSource
import com.bpn.trainme.domain.model.Exercise
import com.bpn.trainme.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val localDataSource: LocalExerciseDataSource,
    private val remoteDataSource: RemoteExerciseDataSource
) : ExerciseRepository {

    companion object{
        private const val PAGE_SIZE = 10
    }

    private var currentPagingSource : MediatorPagingSource ?= null

    override fun getExercises(): Flow<PagingData<Exercise>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MediatorPagingSource(localDataSource, remoteDataSource).also {
                    currentPagingSource = it
                }
            }
        ).flow
    }

    override suspend fun clearExercises() {
        localDataSource.clearExercises()
    }

}