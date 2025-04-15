package com.bpn.trainme.data.remote

import com.bpn.trainme.data.remote.dto.ExerciseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExerciseApi {

    @GET("api/v1/exercises")
    suspend fun getExercises(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ExerciseResponse
}