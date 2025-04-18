package com.bpn.trainme.data.remote.dto

data class ExerciseResponse(
    val success: Boolean,
    val data: ExerciseData?
)

data class ExerciseData(
    val previousPage: String?,
    val nextPage: String?,
    val totalPages: Int?,
    val totalExercises: Int?,
    val currentPage: Int?,
    val exercises: List<ExerciseDto>?
)

data class ExerciseDto(
    val exerciseId: String?,
    val name: String?,
    val gifUrl: String?,
    val instructions: List<String>?,
    val targetMuscles: List<String>?,
    val bodyParts: List<String>?,
    val equipments: List<String>?,
    val secondaryMuscles: List<String>?
)