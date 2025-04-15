package com.bpn.trainme.data.datasource

import com.bpn.trainme.data.remote.ExerciseApi

class RemoteExerciseDataSource(
    private val api: ExerciseApi
){
    suspend fun getExercises(offset: Int, limit: Int) = api.getExercises(offset, limit)
}