package com.example.smarttasktracker.domain.usecase.tasks

import com.example.smarttasktracker.domain.model.Priority
import com.example.smarttasktracker.domain.model.TaskItem
import com.example.smarttasktracker.domain.repository.TasksRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDate

class TaskUseCasesTest {
    private val repository: TasksRepository = mockk()

    private lateinit var getAllTasks: GetAllTasks
    private lateinit var getTaskById: GetTaskById
    private lateinit var insertTask: InsertTask
    private lateinit var updateTask: UpdateTask
    private lateinit var deleteTask: DeleteTask

    @Before
    fun setup() {
        getAllTasks = GetAllTasks(repository)
        getTaskById = GetTaskById(repository)
        insertTask = InsertTask(repository)
        updateTask = UpdateTask(repository)
        deleteTask = DeleteTask(repository)
    }

    @Test
    fun `getAllTasks should return tasks from repository`() = runTest {
        val tasks = listOf(
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
        every { repository.getAllTasks() } returns flowOf(tasks)
        val result = getAllTasks().first()
        assertEquals(1, result.size)
        assertEquals("Test Task", result[0].title)
    }

    @Test
    fun `getTaskById should return task from repository`() = runTest {
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
        coEvery { repository.getTaskById(1) } returns task
        val result = getTaskById(1)
        assertEquals(task, result)
    }

    @Test
    fun `insertTask should call repository`() = runTest {
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
        coEvery { repository.insertTask(any()) } just Runs
        insertTask(task)
        coVerify { repository.insertTask(task) }
    }

    @Test
    fun `deleteTask should call repository`() = runTest {
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
        coEvery { repository.deleteTask(any()) } just Runs
        deleteTask(task)
        coVerify { repository.deleteTask(task) }
    }

    @Test
    fun `updateTask should call repository`() = runTest {
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
        coEvery { repository.updateTask(any()) } just Runs
        updateTask(task)
        coVerify { repository.updateTask(task) }
    }
}