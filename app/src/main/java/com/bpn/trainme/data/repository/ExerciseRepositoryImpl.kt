package com.bpn.trainme.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bpn.trainme.data.datasource.LocalExerciseDataSource
import com.bpn.trainme.data.datasource.MediatorPagingSource
import com.bpn.trainme.data.datasource.RemoteExerciseDataSource
import com.bpn.trainme.data.mapper.ExerciseMapper
import com.bpn.trainme.data.remote.ExerciseApi
import com.bpn.trainme.domain.model.Exercise
import com.bpn.trainme.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
//    private val api: ExerciseApi
    private val localDataSource: LocalExerciseDataSource,
    private val remoteDataSource: RemoteExerciseDataSource
) : ExerciseRepository {

    //    override suspend fun getExercises(
//        offset: Int,
//        limit: Int
//    ): List<Exercise> {
//        val response = api.getExercises(offset, limit)
//        return if(response.success && response.data != null && response.data.exercises?.isNotEmpty() == true){
//            response.data.exercises.map {
//                ExerciseMapper.toDomain(it)
//            }
//        }else{
//            emptyList()
//        }
//
//    }
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

}