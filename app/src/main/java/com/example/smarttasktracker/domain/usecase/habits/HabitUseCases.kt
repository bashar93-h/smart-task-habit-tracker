package com.example.smarttasktracker.domain.usecase.habits

import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.repository.HabitsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllHabits @Inject constructor(private val habitRepository: HabitsRepository) {
    operator fun invoke(): Flow<List<HabitItem>> =
        habitRepository.getALlHabits()
}

class GetHabitById @Inject constructor(private val habitRepository: HabitsRepository) {
    suspend operator fun invoke(habitId: Int): HabitItem =
        habitRepository.getHabitById(habitId)
}

class DeleteHabit @Inject constructor(private val habitRepository: HabitsRepository) {
    suspend operator fun invoke(habit: HabitItem) {
        habitRepository.deleteHabit(habit)
    }
}

class InsertHabit @Inject constructor(private val habitRepository: HabitsRepository) {
    suspend operator fun invoke(habit: HabitItem) {
        habitRepository.insertHabit(habit)
    }
}

class UpdateHabit @Inject constructor(private val habitRepository: HabitsRepository) {
    suspend operator fun invoke(habit: HabitItem) {
        habitRepository.updateHabit(habit)
    }
}

data class HabitUseCases @Inject constructor(
    val getAllHabits: GetAllHabits,
    val getHabitById: GetHabitById,
    val insertHabit: InsertHabit,
    val deleteHabit: DeleteHabit,
    val updateHabit: UpdateHabit
)