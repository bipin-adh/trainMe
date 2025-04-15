package com.bpn.trainme.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise WHERE pageIndex = :pageIndex ORDER BY name LIMIT :limit OFFSET :offset")
    suspend fun getExercises(pageIndex: Int, limit : Int, offset : Int): List<ExerciseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<ExerciseEntity>)

}