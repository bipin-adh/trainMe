package com.bpn.trainme.data.datasource

import com.bpn.trainme.data.local.ExerciseDao
import com.bpn.trainme.data.local.ExerciseEntity
import com.bpn.trainme.data.mapper.ExerciseMapper
import com.bpn.trainme.domain.model.Exercise

class LocalExerciseDataSource(
    private val dao : ExerciseDao
){
    companion object{
        private const val MAX_EXERCISES = 50
        private const val PAGE_SIZE = 10
    }

    suspend fun getExercises(pageIndex: Int, offset: Int, limit: Int): List<Exercise> {
        return dao.getExercises(pageIndex, limit, offset).map {
            ExerciseMapper.toDomain(it)
        }
    }

    suspend fun insertExercises(exercises: List<ExerciseEntity>){
        dao.insertExercises(exercises = exercises)
    }

    suspend fun clearExercises(){
        dao.clearExercises()
    }
}