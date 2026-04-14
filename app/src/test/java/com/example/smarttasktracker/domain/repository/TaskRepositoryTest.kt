package com.example.smarttasktracker.domain.repository

import com.example.smarttasktracker.core.notification.ReminderScheduler
import com.example.smarttasktracker.data.datasources.local.dao.TaskDao
import com.example.smarttasktracker.data.datasources.local.entity.TaskEntity
import com.example.smarttasktracker.data.datasources.local.repository.TasksRepositoryImpl
import com.example.smarttasktracker.domain.model.Priority
import com.example.smarttasktracker.domain.model.TaskItem
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class TaskRepositoryImplTest {
    private lateinit var repository: TasksRepositoryImpl
    private val dao: TaskDao = mockk()
    private val reminderScheduler: ReminderScheduler = mockk()

    @Before
    fun setup() {
        repository = TasksRepositoryImpl(dao, reminderScheduler)
    }

    @Test
    fun `getAllTasks() should return mapped domain tasks`() = runTest {
        val entities = listOf(
            TaskEntity(
                id = 1,
                title = "Test Task",
                description = "Description",
                time = "08:00 AM",
                date = "2026-04-09",
                priority = "HIGH",
                category = "Personal",
                isCompleted = false,
                notes = "Some Notes",
                reminder = "None"
            )
        )
        every { dao.getAllTasks() } returns flowOf(entities)
        val result = repository.getAllTasks().first()
        assertEquals(1, result.size)
        assertEquals("Test Task", result[0].title)
        assertEquals(Priority.HIGH, result[0].priority)
        assertEquals(LocalDate.parse("2026-04-09"), result[0].date)
    }

    @Test
    fun `getTaskById() should return mapped task`() = runTest {
        val entity = TaskEntity(
            id = 1,
            title = "Test Task",
            description = "Description",
            time = "08:00 AM",
            date = "2026-04-09",
            priority = "HIGH",
            category = "Personal",
            isCompleted = false,
            notes = "Some Notes",
            reminder = "None"
        )
        coEvery { dao.getTaskById(1) } returns entity
        val result = repository.getTaskById(1)
        assertEquals("Test Task", result.title)
        assertEquals(Priority.HIGH, result.priority)

    }

    @Test
    fun `insertTask should call dao with mapped entity`() = runTest {
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
        coEvery { dao.insertTask(any()) } just Runs
        repository.insertTask(task)

        coVerify {
            dao.insertTask(match {
                it.id == task.id && it.priority == "HIGH"
            })
        }
    }

    @Test
    fun `deleteTask should call dao with mapped entity`() = runTest {
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
        coEvery { dao.deleteTask(any()) } just Runs
        repository.deleteTask(task)

        coVerify {
            dao.deleteTask(match {
                it.id == task.id && it.priority == "HIGH"
            })
        }
    }

    @Test
    fun `updateTask should call dao with mapped entity`() = runTest {
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
        coEvery { dao.updateTask(any()) } just Runs
        repository.updateTask(task)

        coVerify {
            dao.updateTask(match {
                it.id == task.id && it.priority == "HIGH"
            })
        }
    }
}