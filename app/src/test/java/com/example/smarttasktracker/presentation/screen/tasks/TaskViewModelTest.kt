package com.example.smarttasktracker.presentation.screen.tasks

import com.example.smarttasktracker.domain.model.Priority
import com.example.smarttasktracker.domain.model.TaskItem
import com.example.smarttasktracker.domain.usecase.tasks.TaskUseCases
import com.example.smarttasktracker.presentation.screens.tasks.TasksViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class TaskViewModelTest {
    private val useCases: TaskUseCases = mockk()
    private lateinit var viewModel: TasksViewModel

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
    fun `loadTasks should update tasks state`() = runTest {
        val taskList = listOf(
            TaskItem(
                id = 1,
                title = "Test Task",
                description = "Description",
                time = "08:00 AM",
                date = LocalDate.now(),
                priority = Priority.HIGH,
                category = "Personal",
                isCompleted = false,
                notes = "Some Notes",
                reminder = "None"
            )
        )
        coEvery { useCases.getAllTasks() } returns flowOf(taskList)
        viewModel = TasksViewModel(useCases)
        dispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.tasks.value
        assertEquals(1, result.size)
        assertEquals("Test Task", result[0].title)
    }

    @Test
    fun `getTaskById should update selectedTasks state`() = runTest {
        val task = TaskItem(
            id = 1,
            title = "Test Task",
            description = "Description",
            time = "08:00 AM",
            date = LocalDate.now(),
            priority = Priority.HIGH,
            category = "Personal",
            isCompleted = false,
            notes = "Some Notes",
            reminder = "None"
        )
        coEvery { useCases.getAllTasks() } returns flowOf(emptyList())
        coEvery { useCases.getTaskById(1) } returns task
        viewModel = TasksViewModel(useCases)
        viewModel.getTaskById(1)
        dispatcher.scheduler.advanceUntilIdle()
        val result = viewModel.selectedTask.value
        assertEquals("Test Task", result?.title)
    }

    @Test
    fun `addTask should call insert use case`() = runTest {
        val task = TaskItem(
            id = 1,
            title = "Test Task",
            description = "Description",
            time = "08:00 AM",
            date = LocalDate.now(),
            priority = Priority.HIGH,
            category = "Personal",
            isCompleted = false,
            notes = "Some Notes",
            reminder = "None"
        )
        coEvery { useCases.getAllTasks() } returns flowOf(emptyList())
        coEvery { useCases.insertTask(any()) } just Runs
        viewModel = TasksViewModel(useCases)
        viewModel.addTask(task)
        dispatcher.scheduler.advanceUntilIdle()
        coVerify { useCases.insertTask(task) }
    }

    @Test
    fun `updateTask should call update use case and update selectedTask if ids match`() = runTest {
        val task = TaskItem(
            id = 1,
            title = "Test Task",
            description = "Description",
            time = "08:00 AM",
            date = LocalDate.now(),
            priority = Priority.HIGH,
            category = "Personal",
            isCompleted = false,
            notes = "Some Notes",
            reminder = "None"
        )
        coEvery { useCases.getAllTasks() } returns flowOf(emptyList())
        coEvery { useCases.updateTask(any()) } just Runs
        coEvery { useCases.getTaskById(1) } returns task
        viewModel = TasksViewModel(useCases)
        viewModel.getTaskById(1)
        viewModel.updateTask(task)
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(task, viewModel.selectedTask.value)
        coVerify { useCases.updateTask(task) }
    }

    @Test
    fun `deleteTask should call delete use case`() = runTest {
        val task = TaskItem(
            id = 1,
            title = "Test Task",
            description = "Description",
            time = "08:00 AM",
            date = LocalDate.now(),
            priority = Priority.HIGH,
            category = "Personal",
            isCompleted = false,
            notes = "Some Notes",
            reminder = "None"
        )
        coEvery { useCases.getAllTasks() } returns flowOf(emptyList())
        coEvery { useCases.deleteTask(any()) } just Runs
        viewModel = TasksViewModel(useCases)
        viewModel.deleteTask(task)
        dispatcher.scheduler.advanceUntilIdle()
        coVerify { useCases.deleteTask(task) }
    }
}