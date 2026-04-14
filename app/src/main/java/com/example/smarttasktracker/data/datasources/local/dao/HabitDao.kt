package com.example.smarttasktracker.data.datasources.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.smarttasktracker.data.datasources.local.entity.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * from habits")
    fun getAllHabits(): Flow<List<HabitEntity>>

    @Query("SELECT * From habits where id = :habitId LIMIT 1")
    suspend fun getHabitById(habitId: Int): HabitEntity

    @Delete
    suspend fun deleteHabit(habit: HabitEntity)

    @Insert
    suspend fun insertHabit(habit: HabitEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHabit(habit: HabitEntity)
}