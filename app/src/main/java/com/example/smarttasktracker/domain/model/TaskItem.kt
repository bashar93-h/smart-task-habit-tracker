package com.example.smarttasktracker.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import java.time.LocalDate


data class TaskItem(
    val id: Int,
    val title: String,
    val description: String = "",
    val time: String,
    val category: String,
    val isCompleted: Boolean,
    val priority: Priority,
    val date: LocalDate,
    val notes: String = "",
    val reminder: String = "None"
)

enum class TaskFilter { ALL, TODAY, UPCOMING, COMPLETED }
enum class Priority { HIGH, MEDIUM, LOW }

