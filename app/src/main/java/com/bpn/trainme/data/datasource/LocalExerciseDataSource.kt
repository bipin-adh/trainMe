package com.bpn.trainme.data.datasource

import com.bpn.trainme.data.local.ExerciseDao
import com.bpn.trainme.data.local.ExerciseEntity
import com.bpn.trainme.data.mapper.toDomain
import com.bpn.trainme.domain.model.Exercise

class LocalExerciseDataSource(
    private val dao: ExerciseDao
) {
    suspend fun getExercises(pageIndex: Int, offset: Int, limit: Int): List<Exercise> {
        return dao.getExercises(pageIndex, limit, offset).map {
            it.toDomain()
        }
    }

    suspend fun insertExercises(exercises: List<ExerciseEntity>) {
        dao.insertExercises(exercises = exercises)
    }

    suspend fun clearExercises() {
        dao.clearExercises()
    }
}