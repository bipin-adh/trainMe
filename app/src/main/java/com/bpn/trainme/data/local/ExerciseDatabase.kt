package com.bpn.trainme.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExerciseEntity::class], version = 1, exportSchema = false)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao() : ExerciseDao
}