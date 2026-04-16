package com.example.smarttasktracker.data.datasources.local.repository

import com.example.smarttasktracker.core.notification.ReminderScheduler
import com.example.smarttasktracker.data.datasources.local.dao.HabitDao
import com.example.smarttasktracker.data.datasources.local.mapper.toDomain
import com.example.smarttasktracker.data.datasources.local.mapper.toEntity
import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.repository.HabitsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HabitsRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao,
    private val reminderScheduler: ReminderScheduler
) : HabitsRepository {
    override fun getALlHabits(): Flow<List<HabitItem>> =
        habitDao.getAllHabits().map { list -> list.map { it.toDomain() } }

    override suspend fun getHabitById(habitId: Int): HabitItem =
        habitDao.getHabitById(habitId).toDomain()

    override suspend fun insertHabit(habit: HabitItem) {
        habitDao.insertHabit(habit.toEntity())
        reminderScheduler.scheduleHabitReminder(habit)
    }

    override suspend fun deleteHabit(habit: HabitItem) {
        habitDao.deleteHabit(habit.toEntity())
        reminderScheduler.cancelHabitReminder(habit.id)
    }

    override suspend fun updateHabit(habit: HabitItem) {
        habitDao.updateHabit(habit.toEntity())
        reminderScheduler.cancelHabitReminder(habit.id)
        reminderScheduler.scheduleHabitReminder(habit)
    }
}