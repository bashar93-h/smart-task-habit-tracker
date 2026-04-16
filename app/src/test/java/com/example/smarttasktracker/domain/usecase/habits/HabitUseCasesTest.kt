package com.example.smarttasktracker.domain.usecase.habits

import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.model.HabitType
import com.example.smarttasktracker.domain.repository.HabitsRepository
import compose.icons.FeatherIcons
import compose.icons.feathericons.Music
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HabitUseCasesTest {
    private val repository: HabitsRepository = mockk()
    private lateinit var getAllHabits: GetAllHabits
    private lateinit var getHabitById: GetHabitById
    private lateinit var insertHabit: InsertHabit
    private lateinit var deleteHabit: DeleteHabit
    private lateinit var updateHabit: UpdateHabit

    @Before
    fun setup() {
        getAllHabits = GetAllHabits(repository)
        getHabitById = GetHabitById(repository)
        insertHabit = InsertHabit(repository)
        deleteHabit = DeleteHabit(repository)
        updateHabit = UpdateHabit(repository)
    }

    @Test
    fun `getAllHabits should return habits from repository`() = runTest {
        val habits = listOf(
            HabitItem(
                id = 1,
                title = "Habit Test",
                category = "Personal",
                icon = FeatherIcons.Music,
                type = HabitType.TIME,
                targetCount = 10,
                currentCount = 3,
                unit = "mins",
                isDone = false,
                streak = 5,
                reminderTime = "08:00 PM",
                weeklyDone = List(7) { false }
            )
        )

        every { repository.getALlHabits() } returns flowOf(habits)
        val result = getAllHabits().first()
        assertEquals(1, result.size)
        assertEquals("Habit Test", result[0].title)
    }

    @Test
    fun `getHabitById should return habit from repository`() = runTest {
        val habit = HabitItem(
            id = 1,
            title = "Habit Test",
            category = "Personal",
            icon = FeatherIcons.Music,
            type = HabitType.TIME,
            targetCount = 10,
            currentCount = 3,
            unit = "mins",
            isDone = false,
            streak = 5,
            reminderTime = "08:00 PM",
            weeklyDone = List(7) { false }
        )
        coEvery { repository.getHabitById(1) } returns habit
        val result = getHabitById(1)
        assertEquals(habit, result)
    }

    @Test
    fun `insertHabit should call repository`() = runTest {
        val habit = HabitItem(
            id = 1,
            title = "Habit Test",
            category = "Personal",
            icon = FeatherIcons.Music,
            type = HabitType.TIME,
            targetCount = 10,
            currentCount = 3,
            unit = "mins",
            isDone = false,
            streak = 5,
            reminderTime = "08:00 PM",
            weeklyDone = List(7) { false }
        )
        coEvery { repository.insertHabit(any()) } just Runs
        insertHabit(habit)
        coVerify { repository.insertHabit(habit) }
    }

    @Test
    fun `deleteHabit should call repository`() = runTest {
        val habit = HabitItem(
            id = 1,
            title = "Habit Test",
            category = "Personal",
            icon = FeatherIcons.Music,
            type = HabitType.TIME,
            targetCount = 10,
            currentCount = 3,
            unit = "mins",
            isDone = false,
            streak = 5,
            reminderTime = "08:00 PM",
            weeklyDone = List(7) { false }
        )
        coEvery { repository.deleteHabit(any()) } just Runs
        deleteHabit(habit)
        coVerify { repository.deleteHabit(habit) }
    }

    @Test
    fun `updateHabit should call repository`() = runTest {
        val habit = HabitItem(
            id = 1,
            title = "Habit Test",
            category = "Personal",
            icon = FeatherIcons.Music,
            type = HabitType.TIME,
            targetCount = 10,
            currentCount = 3,
            unit = "mins",
            isDone = false,
            streak = 5,
            reminderTime = "08:00 PM",
            weeklyDone = List(7) { false }
        )
        coEvery { repository.updateHabit(any()) } just Runs
        updateHabit(habit)
        coVerify { repository.updateHabit(habit) }
    }

}