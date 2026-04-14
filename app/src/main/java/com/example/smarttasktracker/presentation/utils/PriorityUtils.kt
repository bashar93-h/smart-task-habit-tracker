package com.example.smarttasktracker.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.smarttasktracker.domain.model.Priority

@Composable
fun Priority.color() = when (this) {
    Priority.HIGH -> Color(0xFFEF5350)
    Priority.MEDIUM -> Color(0xFFFF9800)
    Priority.LOW -> Color(0xFF66BB6A)
}

fun Priority.label() = when (this) {
    Priority.HIGH -> "High"
    Priority.MEDIUM -> "Med"
    Priority.LOW -> "Low"
}
