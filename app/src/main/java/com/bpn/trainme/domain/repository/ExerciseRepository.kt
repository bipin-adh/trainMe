package com.bpn.trainme.domain.repository

import androidx.paging.PagingData
import com.bpn.trainme.domain.model.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
//    suspend fun getExercises(offset: Int, limit: Int): List<Exercise>
     fun getExercises(): Flow<PagingData<Exercise>>
}