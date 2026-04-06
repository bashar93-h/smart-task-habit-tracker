package com.example.smarttasktracker.domain.model

import androidx.compose.ui.graphics.vector.ImageVector


data class HabitItem(
    val id: Int,
    val title: String,
    val category: String,
    val icon: ImageVector,
    val type: HabitType,
    val targetCount: Int = 1,
    val currentCount: Int = 0,
    val unit: String = "",
    val isDone: Boolean = false,
    val streak: Int = 0,
    val reminderTime: String = "No Reminder",
    val weeklyDone: List<Boolean> = List(7) { false }
)

enum class HabitType { SIMPLE, COUNT, TIME }