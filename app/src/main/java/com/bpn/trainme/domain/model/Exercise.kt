package com.bpn.trainme.domain.model

data class Exercise(
    val exerciseId: String,
    val name: String,
    val gifUrl: String,
    val instructions: List<String>,
    val targetMuscles: List<String>,
    val bodyParts: List<String>,
    val equipments: List<String>,
    val secondaryMuscles: List<String>
)