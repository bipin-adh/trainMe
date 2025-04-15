package com.bpn.trainme.domain.usecase

import com.bpn.trainme.domain.model.Exercise
import com.bpn.trainme.domain.repository.ExerciseRepository
import javax.inject.Inject

class GetExerciseUseCase @Inject constructor(
    private val repository: ExerciseRepository
) {
    companion object{
        const val DEFAULT_LIMIT = 10
    }

    suspend operator fun invoke(offset: Int, limit: Int = DEFAULT_LIMIT) : List<Exercise> {
        return repository.getExercises(offset,limit)
    }
}