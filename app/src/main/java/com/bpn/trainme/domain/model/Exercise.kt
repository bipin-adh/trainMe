package com.bpn.trainme.domain.model

data class Exercise(
    val id : Int,
    val name : String,
    val bodyPart : String,
    val equipment : String,
    val gifUrl : String,
    val target : String,
    val secondaryMuscles : List<String>,
    val instructions : List<String>
)