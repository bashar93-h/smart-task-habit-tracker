package com.example.smarttasktracker.presentation.screen.habits

import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.model.HabitType
import com.example.smarttasktracker.domain.usecase.habits.HabitUseCases
import com.example.smarttasktracker.presentation.screens.habits.HabitsViewModel
import compose.icons.FeatherIcons
import compose.icons.feathericons.Music
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HabitViewModelTest {
    private val useCases: HabitUseCases = mockk()
    private lateinit var viewModel: HabitsViewModel

    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadHabits should update habits state`() = runTest {
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
        coEvery { useCases.getAllHabits() } returns flowOf(habits)
        viewModel = HabitsViewModel(useCases)
        dispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.habits.value
        assertEquals(1, result.size)
        assertEquals("Habit Test", result[0].title)
    }

    @Test
    fun `getHabitById should update habit state`() = runTest {
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
        coEvery { useCases.getAllHabits() } returns flowOf(emptyList())
        coEvery { useCases.getHabitById(1) } returns habit
        viewModel = HabitsViewModel(useCases)
        viewModel.getHabitById(1)
        dispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.selectedHabit.value
        assertEquals("Habit Test", result?.title)
    }

    @Test
    fun `addHabit should call insert use case`() = runTest {
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
        coEvery { useCases.getAllHabits() } returns flowOf(emptyList())
        coEvery { useCases.insertHabit(any()) } just Runs
        viewModel = HabitsViewModel(useCases)
        viewModel.addHabit(habit)
        dispatcher.scheduler.advanceUntilIdle()
        coVerify { useCases.insertHabit(habit) }
    }

    @Test
    fun `updateHabit should call update use case`() = runTest {
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
        coEvery { useCases.getAllHabits() } returns flowOf(emptyList())
        coEvery { useCases.updateHabit(any()) } just Runs
        viewModel = HabitsViewModel(useCases)
        viewModel.updateHabit(habit)
        dispatcher.scheduler.advanceUntilIdle()
        coVerify { useCases.updateHabit(habit) }
    }

    @Test
    fun `deleteHabit should call delete use case`() = runTest {
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
        coEvery { useCases.getAllHabits() } returns flowOf(emptyList())
        coEvery { useCases.deleteHabit(any()) } just Runs
        viewModel = HabitsViewModel(useCases)
        viewModel.deleteHabit(habit)
        dispatcher.scheduler.advanceUntilIdle()
        coVerify { useCases.deleteHabit(habit) }
    }
}