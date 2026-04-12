package com.example.smarttasktracker.data.local.mapper

import com.example.smarttasktracker.data.datasources.local.entity.TaskEntity
import com.example.smarttasktracker.data.datasources.local.mapper.toDomain
import com.example.smarttasktracker.data.datasources.local.mapper.toEntity
import com.example.smarttasktracker.domain.model.Priority
import com.example.smarttasktracker.domain.model.TaskItem
import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDate

class TaskMapperTest {
    @Test
    fun `toDomain should map TaskEntity to TaskItem correctly`() {
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

        val domain = entity.toDomain()
        assertEquals(entity.id, domain.id)
        assertEquals(entity.title, domain.title)
        assertEquals(entity.description, domain.description)
        assertEquals(entity.time, domain.time)
        assertEquals(LocalDate.parse(entity.date), domain.date)
        assertEquals(Priority.HIGH, domain.priority)
        assertEquals(entity.notes, domain.notes)
        assertEquals(entity.category, domain.category)
        assertEquals(entity.isCompleted, domain.isCompleted)
        assertEquals(entity.reminder, domain.reminder)
    }

    @Test
    fun `toEntity should map TaskItem to TaskEntity correctly`() {
        val domain = TaskItem(
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

        val entity = domain.toEntity()

        assertEquals(domain.id, entity.id)
        assertEquals(domain.title, entity.title)
        assertEquals(domain.description, entity.description)
        assertEquals(domain.time, entity.time)
        assertEquals(domain.date.toString(), entity.date)
        assertEquals("HIGH", entity.priority)
        assertEquals(domain.category, entity.category)
        assertEquals(domain.isCompleted, entity.isCompleted)
        assertEquals(domain.notes, entity.notes)
        assertEquals(domain.reminder, entity.reminder)
    }
}