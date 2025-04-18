package com.bpn.trainme.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ExerciseEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao() : ExerciseDao
}