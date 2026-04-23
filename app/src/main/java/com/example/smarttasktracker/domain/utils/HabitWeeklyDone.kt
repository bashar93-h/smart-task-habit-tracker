package com.example.smarttasktracker.domain.utils

import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.model.HabitType
import java.time.DayOfWeek
import java.time.LocalDate

object HabitWeeklyDone {

    /** Monday = 0 … Sunday = 6 (matches your `HabitCard` labels M…S). */
    fun mondayFirstIndex(date: LocalDate): Int = date.dayOfWeek.value - 1

    fun mondayEpochDay(date: LocalDate): Long =
        date.with(DayOfWeek.MONDAY).toEpochDay()

    fun normalizeSeven(flags: List<Boolean>): List<Boolean> =
        List(7) { index -> flags.getOrElse(index) { false } }

    fun alignWeekFlags(
        weeklyDone: List<Boolean>,
        storedMondayEpoch: Long,
        today: LocalDate = LocalDate.now()
    ): Pair<List<Boolean>, Long> {
        val thisMonday = mondayEpochDay(today)
        val normalized = normalizeSeven(weeklyDone)

        return when {
            storedMondayEpoch == 0L -> normalized to thisMonday
            storedMondayEpoch != thisMonday -> List(7) { false } to thisMonday
            else -> normalized to storedMondayEpoch
        }
    }

    fun habitDoneForToday(habit: HabitItem): Boolean =
        when (habit.type) {
            HabitType.SIMPLE -> habit.isDone
            HabitType.COUNT,
            HabitType.TIME -> habit.currentCount >= habit.targetCount
        }

    fun applyForToday(
        habit: HabitItem,
        doneForToday: Boolean,
        today: LocalDate = LocalDate.now()
    ): HabitItem {
        val (flags, mondayEpoch) = alignWeekFlags(
            weeklyDone = habit.weeklyDone,
            storedMondayEpoch = habit.weeklyWeekStartEpochDay,
            today = today
        )
        val idx = mondayFirstIndex(today)
        val updated = flags.toMutableList().also { it[idx] = doneForToday }
        return habit.copy(
            weeklyDone = updated,
            weeklyWeekStartEpochDay = mondayEpoch
        )
    }
}

// PurDomain layer: handles core business rules for habit weekly progress (week alignment, completion logic), independent of UI and database