package com.bpn.trainme.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val pageIndex: Int // tracks page for pagination
)