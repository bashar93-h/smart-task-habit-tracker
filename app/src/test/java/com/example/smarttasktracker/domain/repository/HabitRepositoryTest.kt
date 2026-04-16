package com.example.smarttasktracker.domain.repository

import com.example.smarttasktracker.data.datasources.local.dao.HabitDao
import com.example.smarttasktracker.data.datasources.local.entity.HabitEntity
import com.example.smarttasktracker.data.datasources.local.repository.HabitsRepositoryImpl
import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.model.HabitType
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

class HabitRepositoryImplTest() {
    private lateinit var repository: HabitsRepositoryImpl

    private val dao: HabitDao = mockk()

    @Before
    fun setup() {
        repository = HabitsRepositoryImpl(dao)
    }

    @Test
    fun `getAllHabits should return mapped domain habits`() = runTest {
        val entities = listOf(
            HabitEntity(
                id = 1,
                title = "Habit Test",
                category = "Personal",
                icon = "Music",
                type = "TIME",
                targetCount = 10,
                currentCount = 3,
                unit = "mins",
                isDone = false,
                streak = 5,
                reminderTime = "08:00 PM",
                weeklyDone = List(7) { false }
            )
        )
        every { dao.getAllHabits() } returns flowOf(entities)
        val result = repository.getALlHabits().first()
        assertEquals(1, result.size)
        assertEquals("Habit Test", result[0].title)
        assertEquals(FeatherIcons.Music, result[0].icon)
        assertEquals(HabitType.TIME, result[0].type)
    }

    @Test
    fun `getHabitById should return mapped domain habit`() = runTest {
        val entity = HabitEntity(
            id = 1,
            title = "Habit Test",
            category = "Personal",
            icon = "Music",
            type = "TIME",
            targetCount = 10,
            currentCount = 3,
            unit = "mins",
            isDone = false,
            streak = 5,
            reminderTime = "08:00 PM",
            weeklyDone = List(7) { false }
        )
        coEvery { dao.getHabitById(1) } returns entity
        val result = repository.getHabitById(1)
        assertEquals("Habit Test", result.title)
        assertEquals(FeatherIcons.Music, result.icon)
        assertEquals(HabitType.TIME, result.type)
    }

    @Test
    fun `insertHabit should call dao with mapped entity`() = runTest {
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
        coEvery { dao.insertHabit(any()) } just Runs
        repository.insertHabit(habit)
        coVerify {
            dao.insertHabit(match {
                it.id == habit.id && it.icon == "Music" && it.type == "TIME"
            })
        }
    }

    @Test
    fun `deleteHabit should call dao with mapped entity`() = runTest {
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
        coEvery { dao.deleteHabit(any()) } just Runs
        repository.deleteHabit(habit)
        coVerify {
            dao.deleteHabit(match {
                it.id == habit.id && it.icon == "Music" && it.type == "TIME"
            })
        }
    }

    @Test
    fun `updateHabit should call dao with mapped entity`() = runTest {
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
        coEvery { dao.updateHabit(any()) } just Runs
        repository.updateHabit(habit)
        coVerify {
            dao.updateHabit(match {
                it.id == habit.id && it.icon == "Music" && it.type == "TIME"
            })
        }
    }
}