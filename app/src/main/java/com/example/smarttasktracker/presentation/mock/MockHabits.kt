package com.example.smarttasktracker.presentation.mock

import com.example.smarttasktracker.domain.model.HabitItem
import com.example.smarttasktracker.domain.model.HabitType
import compose.icons.FeatherIcons
import compose.icons.feathericons.Activity
import compose.icons.feathericons.BookOpen
import compose.icons.feathericons.Droplet
import compose.icons.feathericons.Edit
import compose.icons.feathericons.Moon
import compose.icons.feathericons.Sunset

val mockHabits = listOf(
    HabitItem(
        id = 1,
        title = "Drink Water",
        category = "Health",
        icon = FeatherIcons.Droplet,
        type = HabitType.COUNT,
        targetCount = 8,
        currentCount = 6,
        unit = "glasses",
        streak = 7,
        weeklyDone = listOf(true, true, true, true, false, true, false)
    ),
    HabitItem(
        id = 2,
        title = "Meditate",
        category = "Mindfulness",
        icon = FeatherIcons.Moon,
        type = HabitType.TIME,
        targetCount = 20,
        currentCount = 10,
        unit = "min",
        streak = 3,
        weeklyDone = listOf(true, true, false, true, false, false, false)
    ),
    HabitItem(
        id = 3,
        title = "Exercise",
        category = "Health",
        icon = FeatherIcons.Activity,
        type = HabitType.SIMPLE,
        isDone = true,
        streak = 12,
        weeklyDone = listOf(true, true, true, true, true, false, false)
    ),
    HabitItem(
        id = 4,
        title = "Read",
        category = "Personal",
        icon = FeatherIcons.BookOpen,
        type = HabitType.COUNT,
        targetCount = 30,
        currentCount = 20,
        unit = "pages",
        streak = 5,
        weeklyDone = listOf(true, false, true, true, false, false, false)
    ),
    HabitItem(
        id = 5,
        title = "Sleep Early",
        category = "Health",
        icon = FeatherIcons.Sunset,
        type = HabitType.SIMPLE,
        isDone = false,
        streak = 1,
        weeklyDone = listOf(true, false, false, true, false, false, false)
    ),
    HabitItem(
        id = 6,
        title = "Journal",
        category = "Personal",
        icon = FeatherIcons.Edit,
        type = HabitType.TIME,
        targetCount = 15,
        currentCount = 15,
        unit = "min",
        streak = 4,
        weeklyDone = listOf(false, true, true, true, false, false, false)
    ),
    HabitItem(
        id = 7,
        title = "Exercise",
        category = "Health",
        icon = FeatherIcons.Activity,
        type = HabitType.SIMPLE,
        isDone = true,
        streak = 12,
        weeklyDone = listOf(true, true, true, true, true, false, false)
    ),
    HabitItem(
        id = 8,
        title = "Journal",
        category = "Personal",
        icon = FeatherIcons.Edit,
        type = HabitType.TIME,
        targetCount = 15,
        currentCount = 15,
        unit = "min",
        streak = 4,
        weeklyDone = listOf(false, true, true, true, false, false, false)
    ),
    HabitItem(
        id = 9,
        title = "Read",
        category = "Personal",
        icon = FeatherIcons.BookOpen,
        type = HabitType.COUNT,
        targetCount = 30,
        currentCount = 20,
        unit = "pages",
        streak = 5,
        weeklyDone = listOf(true, false, true, true, false, false, false)
    ),
)