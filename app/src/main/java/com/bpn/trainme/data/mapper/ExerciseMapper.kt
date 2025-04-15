package com.bpn.trainme.data.mapper

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

}