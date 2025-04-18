package com.bpn.trainme.data.mapper

import com.bpn.trainme.data.local.ExerciseEntity
import com.bpn.trainme.data.remote.dto.ExerciseDto
import com.bpn.trainme.domain.model.Exercise

object ExerciseMapper {

    fun toDomain(dto: ExerciseDto): Exercise =  Exercise(
        exerciseId = dto.exerciseId ?: "",
        name = dto.name?:"",
        gifUrl = dto.gifUrl?:"",
        instructions = dto.instructions ?: emptyList(),
        targetMuscles = dto.targetMuscles ?: emptyList(),
        bodyParts = dto.bodyParts ?: emptyList(),
        equipments = dto.equipments?: emptyList(),
        secondaryMuscles = dto.secondaryMuscles?: emptyList()
    )

    fun toDomain(dto: ExerciseEntity): Exercise = Exercise(
        exerciseId = dto.exerciseId,
        name = dto.name,
        gifUrl = dto.gifUrl,
        instructions = dto.instructions,
        targetMuscles = dto.targetMuscles,
        bodyParts = dto.bodyParts,
        equipments = dto.equipments,
        secondaryMuscles = dto.secondaryMuscles
    )

    fun toEntity(dto: ExerciseDto, pageIndex: Int): ExerciseEntity?{
        val exerciseId = dto.exerciseId ?: return null
        return ExerciseEntity(
            exerciseId = exerciseId,
            name = dto.name?:"",
            gifUrl = dto.gifUrl?:"",
            instructions = dto.instructions?: emptyList(),
            targetMuscles = dto.targetMuscles?: emptyList(),
            bodyParts = dto.bodyParts ?: emptyList(),
            equipments = dto.equipments?: emptyList(),
            secondaryMuscles = dto.secondaryMuscles ?: emptyList(),
            pageIndex = pageIndex
        )
    }
}