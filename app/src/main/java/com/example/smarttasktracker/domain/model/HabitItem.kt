package com.example.smarttasktracker.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class HabitItem(
    val id: Int,
    val title: String,
    val icon: ImageVector,
    val isDone: Boolean,
    val streak: Int
)
