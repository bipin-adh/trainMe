package com.bpn.trainme.domain.repository

import com.bpn.trainme.domain.model.Exercise

interface ExerciseRepository {
    suspend fun getExercises(offset: Int, limit: Int): List<Exercise>
}