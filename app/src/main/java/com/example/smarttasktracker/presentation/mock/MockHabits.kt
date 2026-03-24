package com.example.smarttasktracker.presentation.mock

import com.example.smarttasktracker.domain.model.HabitItem
import compose.icons.FeatherIcons
import compose.icons.feathericons.Activity
import compose.icons.feathericons.BookOpen
import compose.icons.feathericons.Droplet
import compose.icons.feathericons.Moon
import compose.icons.feathericons.Sunset

val mockHabits = listOf(
    HabitItem(1, "Drink Water", FeatherIcons.Droplet, isDone = true, streak = 7),
    HabitItem(2, "Meditate", FeatherIcons.Moon, isDone = false, streak = 3),
    HabitItem(3, "Exercise", FeatherIcons.Activity, isDone = true, streak = 12),
    HabitItem(4, "Read", FeatherIcons.BookOpen, isDone = false, streak = 5),
    HabitItem(5, "Sleep Early", FeatherIcons.Sunset, isDone = false, streak = 1),
)