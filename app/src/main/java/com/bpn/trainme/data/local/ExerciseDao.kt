package com.bpn.trainme.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises WHERE pageIndex = :pageIndex ORDER BY name LIMIT :limit OFFSET :offset")
    suspend fun getExercises(pageIndex: Int, limit : Int, offset : Int): List<ExerciseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<ExerciseEntity>)

    @Query("DELETE FROM exercises")
    suspend fun clearExercises()

    // transaction is used to execute block of code as a single unit of work
    @Transaction
    suspend fun refreshExercises(exercises: List<ExerciseEntity>) {
        clearExercises()
        insertExercises(exercises)
    }
}