package com.bpn.trainme.data.mapper

import com.bpn.trainme.data.local.ExerciseEntity
import com.bpn.trainme.data.remote.dto.ExerciseDto
import com.bpn.trainme.domain.model.Exercise

object ExerciseMapper {

    fun toDomain(dto: ExerciseDto): Exercise =  Exercise(
        id = dto.id ?: -1,
        name = dto.name?:"",
        bodyPart = dto.bodyPart ?:"",
        equipment = dto.equipment?:"",
        gifUrl = dto.gifUrl?:"",
        target = dto.target?:"",
        secondaryMuscles = dto.secondaryMuscles?: emptyList(),
        instructions = dto.instructions ?: emptyList()
    )

    fun toDomain(dto: ExerciseEntity): Exercise = Exercise(
        id = dto.id,
        name = dto.name,
        bodyPart = dto.bodyPart,
        equipment = dto.equipment,
        gifUrl = dto.gifUrl,
        target = dto.target,
        secondaryMuscles = dto.secondaryMuscles.split(",").filter { it.isNotEmpty() },
        instructions = dto.instructions.split(",").filter { it.isNotEmpty() }
    )

    fun toEntity(dto: ExerciseDto, pageIndex: Int): ExerciseEntity?{
        val id = dto.id ?: return null
        return ExerciseEntity(
            id = id,
            name = dto.name?:"",
            bodyPart = dto.bodyPart?:"",
            equipment = dto.equipment?:"",
            gifUrl = dto.gifUrl?:"",
            target = dto.target?:"",
            secondaryMuscles = dto.secondaryMuscles?.joinToString(",") ?: "",
            instructions = dto.instructions?.joinToString(",") ?: "",
            pageIndex = pageIndex
        )
    }

}