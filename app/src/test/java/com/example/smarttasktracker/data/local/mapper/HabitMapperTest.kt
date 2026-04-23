package com.example.smarttasktracker.data.local.mapper

import com.example.smarttasktracker.data.datasources.local.entity.HabitEntity
import com.example.smarttasktracker.data.datasources.local.mapper.toDomain
import com.example.smarttasktracker.data.datasources.local.mapper.toEntity
import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.model.HabitType
import compose.icons.FeatherIcons
import compose.icons.feathericons.Music
import org.junit.Test
import org.junit.Assert.*

class HabitMapperTest {
    @Test
    fun `toDomain should map habitEntity to habitItem correctly`() {
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

        val domain = entity.toDomain()

        assertEquals(entity.id, domain.id)
        assertEquals(FeatherIcons.Music, domain.icon)
        assertEquals(HabitType.TIME, domain.type)
        assertEquals(entity.category, domain.category)
    }

    @Test
    fun `toEntity should map habitItem to habitEntity correctly`() {
        val domain = HabitItem(
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

        val entity = domain.toEntity()

        assertEquals(domain.id, entity.id)
        assertEquals("Music", entity.icon)
        assertEquals("TIME", entity.type)
        assertEquals(domain.category, entity.category)
    }
}