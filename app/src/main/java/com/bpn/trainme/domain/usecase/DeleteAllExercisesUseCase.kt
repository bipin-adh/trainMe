package com.bpn.trainme.domain.usecase

import com.bpn.trainme.domain.repository.ExerciseRepository
import javax.inject.Inject

class DeleteAllExercisesUseCase @Inject constructor(
    val exerciseRepository : ExerciseRepository
) {
    suspend operator fun invoke(){
        exerciseRepository.clearExercises()
    }

}