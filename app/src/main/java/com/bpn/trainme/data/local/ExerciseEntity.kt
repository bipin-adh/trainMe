package com.bpn.trainme.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey val exerciseId: String,
    val name: String,
    val gifUrl: String,
    val instructions: List<String>,
    val targetMuscles: List<String>,
    val bodyParts: List<String>,
    val equipments: List<String>,
    val secondaryMuscles: List<String>,
    val paginationIndex: Int // tracks page for pagination
)