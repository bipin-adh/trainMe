package com.bpn.trainme.data.remote.dto

data class ExerciseResponse(
    val success: Boolean,
    val data: ExerciseData?
)

data class ExerciseData(
    val previousPage : String?,
    val nextPage : String?,
    val totalPages : Int?,
    val totalExercises : Int?,
    val currentPage : Int?,
    val exercises : List<ExerciseDto>?
)

data class ExerciseDto(
    val id : Int?,
    val name : String?,
    val bodyPart : String?,
    val equipment : String?,
    val gifUrl : String?,
    val target : String?,
    val secondaryMuscles : List<String>?,
    val instructions : List<String>?
)