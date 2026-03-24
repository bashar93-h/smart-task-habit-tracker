package com.example.smarttasktracker.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


data class TaskItem(
    val id: Int,
    val title: String,
    val time: String,
    val category: String,
    val isCompleted: Boolean,
    val priority: Priority
)

enum class Priority { HIGH, MEDIUM, LOW }

@Composable
fun Priority.color() = when (this) {
    Priority.HIGH   -> Color(0xFFEF5350)
    Priority.MEDIUM -> Color(0xFFFF9800)
    Priority.LOW    -> Color(0xFF66BB6A)
}