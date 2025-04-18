package com.bpn.trainme.domain.usecase

import androidx.paging.PagingData
import com.bpn.trainme.domain.model.Exercise
import com.bpn.trainme.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExerciseUseCase @Inject constructor(
    private val repository: ExerciseRepository
) {
    operator fun invoke(): Flow<PagingData<Exercise>> {
        return repository.getExercises()
    }
}