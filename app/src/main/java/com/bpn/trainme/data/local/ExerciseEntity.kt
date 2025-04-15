package com.bpn.trainme.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    val target: String,
    val secondaryMuscles: String, // coma separated
    val instructions: String, // coma separated
    val pageIndex: Int // tracks page for pagination
)