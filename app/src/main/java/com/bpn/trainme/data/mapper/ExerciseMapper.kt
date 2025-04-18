package com.bpn.trainme.data.mapper

import com.bpn.trainme.data.local.ExerciseEntity
import com.bpn.trainme.data.remote.dto.ExerciseDto
import com.bpn.trainme.domain.model.Exercise

fun ExerciseDto.toDomain(): Exercise = Exercise(
    exerciseId = exerciseId ?: "",
    name = name ?: "",
    gifUrl = gifUrl ?: "",
    instructions = instructions ?: emptyList(),
    targetMuscles = targetMuscles ?: emptyList(),
    bodyParts = bodyParts ?: emptyList(),
    equipments = equipments ?: emptyList(),
    secondaryMuscles = secondaryMuscles ?: emptyList()
)

fun ExerciseEntity.toDomain(): Exercise = Exercise(
    exerciseId = exerciseId,
    name = name,
    gifUrl = gifUrl,
    instructions = instructions,
    targetMuscles = targetMuscles,
    bodyParts = bodyParts,
    equipments = equipments,
    secondaryMuscles = secondaryMuscles
)

fun ExerciseDto.toEntity(pageIndex: Int): ExerciseEntity? {
    val exerciseId = exerciseId ?: return null
    return ExerciseEntity(
        exerciseId = exerciseId,
        name = name ?: "",
        gifUrl = gifUrl ?: "",
        instructions = instructions ?: emptyList(),
        targetMuscles = targetMuscles ?: emptyList(),
        bodyParts = bodyParts ?: emptyList(),
        equipments = equipments ?: emptyList(),
        secondaryMuscles = secondaryMuscles ?: emptyList(),
        paginationIndex = pageIndex
    )
}
