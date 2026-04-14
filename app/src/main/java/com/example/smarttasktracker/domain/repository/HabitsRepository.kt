package com.example.smarttasktracker.domain.repository

import com.example.smarttasktracker.domain.model.HabitItem
import kotlinx.coroutines.flow.Flow

interface HabitsRepository {
    fun getALlHabits(): Flow<List<HabitItem>>
    suspend fun getHabitById(habitId: Int) : HabitItem
    suspend fun insertHabit(habit: HabitItem)
    suspend fun deleteHabit(habit: HabitItem)
    suspend fun updateHabit(habit: HabitItem)
}