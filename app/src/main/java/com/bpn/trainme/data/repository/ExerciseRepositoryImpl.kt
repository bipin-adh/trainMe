package com.bpn.trainme.data.repository

import com.bpn.trainme.data.mapper.ExerciseMapper
import com.bpn.trainme.data.remote.ExerciseApi
import com.bpn.trainme.domain.model.Exercise
import com.bpn.trainme.domain.repository.ExerciseRepository
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(private val api: ExerciseApi) : ExerciseRepository {

    override suspend fun getExercises(
        offset: Int,
        limit: Int
    ): List<Exercise> {
        val response = api.getExercises(offset, limit)
        return if(response.success && response.data != null && response.data.exercises?.isNotEmpty() == true){
            response.data.exercises.map {
                ExerciseMapper.toDomain(it)
            }
        }else{
            emptyList()
        }

    }

}